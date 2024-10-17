package br.com.trackwayapp.service;

import br.com.trackwayapp.domain.Product;
import br.com.trackwayapp.domain.ProductHistory;
import br.com.trackwayapp.dto.ProductStatusUpdateDto;
import br.com.trackwayapp.dto.response.PostalCodeDetailsResponseDto;
import br.com.trackwayapp.repository.ProductHistoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductHistoryUpdateService {

    private final ProductHistoryRepository productHistoryRepository;

    private final ProductLookupService productLookupService;
    private final ProductDetailsService productDetailsService;
    private final PostalCodeService postalCodeService;

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "update-product-status", groupId = "product-consumer-group")
    public void updateProductStatus(String message) {
        try {
            ProductStatusUpdateDto productStatusUpdate = this.objectMapper.readValue(message, ProductStatusUpdateDto.class);
            log.info("Status update received for product: {}", productStatusUpdate.getProductId());

            Product product = this.productLookupService.getProductById(productStatusUpdate.getProductId());
            ProductHistory productHistory = this.saveProductHistory(product, productStatusUpdate);

            PostalCodeDetailsResponseDto postalCodeDetails = this.postalCodeService.getPostalCodeDetails(productHistory);
            this.productDetailsService.saveProductDetails(productHistory, postalCodeDetails);
        } catch (Exception e) {
            log.error("Error updating product status", e);
        }
    }

    private ProductHistory saveProductHistory(Product product, ProductStatusUpdateDto productStatusUpdate) {
        ProductHistory productHistory = new ProductHistory();
        productHistory.setProduct(product);
        productHistory.setStatus(productStatusUpdate.getStatus());
        productHistory.setPostalCode(productStatusUpdate.getPostalCode());
        productHistory.setUpdateTimestamp(LocalDateTime.now());

        log.info("New history for product {} saved with status: {}", product.getId(), productStatusUpdate.getStatus());
        return this.productHistoryRepository.save(productHistory);
    }
}