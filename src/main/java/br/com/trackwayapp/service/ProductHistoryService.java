package br.com.trackwayapp.service;

import br.com.trackwayapp.domain.Product;
import br.com.trackwayapp.domain.ProductDetails;
import br.com.trackwayapp.domain.ProductHistory;
import br.com.trackwayapp.dto.ProductHistoryDto;
import br.com.trackwayapp.dto.ProductHistoryWithDetailsDto;
import br.com.trackwayapp.dto.ProductStatusUpdateDto;
import br.com.trackwayapp.dto.ProductWithoutDetailsDto;
import br.com.trackwayapp.dto.response.PostalCodeDetailsResponseDto;
import br.com.trackwayapp.dto.ProductDetailsDto;
import br.com.trackwayapp.dto.ProductCompleteDto;
import br.com.trackwayapp.dto.response.ProductHistoryDetailResponseDto;
import br.com.trackwayapp.dto.response.ProductHistoryResponseDto;
import br.com.trackwayapp.enums.ProductHistoryEnum;
import br.com.trackwayapp.repository.ProductHistoryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductHistoryService {

    private final ProductHistoryRepository productHistoryRepository;

    private final ProductLookupService productLookupService;
    private final ProductDetailsService productDetailsService;
    private final PostalCodeService postalCodeService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public ProductHistoryResponseDto getProductHistory(Long productId) {
        Product product = this.productLookupService.getProductById(productId);

        List<ProductHistory> histories = this.getHistoriesByProductId(productId);

        List<ProductHistoryDto> finalHistories = new ArrayList<>();

        for (ProductHistory history : histories) {
            ProductHistoryDto productHistoryDto = new ProductHistoryDto(history);
            finalHistories.add(productHistoryDto);
        }

        ProductWithoutDetailsDto productWithoutDetailsDto = new ProductWithoutDetailsDto(product);
        productWithoutDetailsDto.setHistories(finalHistories);

        return new ProductHistoryResponseDto(productWithoutDetailsDto);

    }

    public ProductHistoryDetailResponseDto getProductHistoryDetails(Long productId) {
        Product product = this.productLookupService.getProductById(productId);

        List<ProductHistory> histories = this.getHistoriesByProductId(productId);

        List<ProductHistoryWithDetailsDto> finalHistories = new ArrayList<>();

        for (ProductHistory history : histories) {
            ProductDetails details = this.productDetailsService.getProductDetailsByProductHistory(history);
            ProductDetailsDto detailsDto = new ProductDetailsDto(details);

            ProductHistoryWithDetailsDto productHistoryWithDetailsDto = new ProductHistoryWithDetailsDto(history);
            productHistoryWithDetailsDto.setDetails(detailsDto);

            finalHistories.add(productHistoryWithDetailsDto);
        }

        ProductCompleteDto productCompleteDto = new ProductCompleteDto(product);
        productCompleteDto.setHistories(finalHistories);

        return new ProductHistoryDetailResponseDto(productCompleteDto);
    }

    @KafkaListener(topics = "update-product-status", groupId = "group_id")
    public void updateProductStatus(String message) throws JsonProcessingException {
        ProductStatusUpdateDto productStatusUpdate = objectMapper.readValue(message, ProductStatusUpdateDto.class);

        log.info("Status update received for product: {}", productStatusUpdate.getProductId());
        Product product = this.productLookupService.getProductById(productStatusUpdate.getProductId());

        ProductHistory productHistory = this.saveProductHistory(product, productStatusUpdate);

        PostalCodeDetailsResponseDto postalCodeDetails = this.postalCodeService.getPostalCodeDetails(productHistory);

        this.productDetailsService.saveProductDetails(productHistory, postalCodeDetails);
    }

    public ProductHistory saveProductHistory(Product product, String currentPostalCode) {
        ProductHistory productHistory = new ProductHistory();
        productHistory.setProduct(product);
        productHistory.setStatus(ProductHistoryEnum.POSTED);
        productHistory.setCurrentPostalCode(currentPostalCode);
        productHistory.setUpdateTimestamp(LocalDateTime.now());

        log.info("New history for product saved with status: {}", ProductHistoryEnum.POSTED);
        return this.productHistoryRepository.save(productHistory);
    }

    private List<ProductHistory> getHistoriesByProductId(Long productId) {
        Product product = this.productLookupService.getProductById(productId);

        return product.getHistory().stream()
            .sorted(Comparator.comparing(ProductHistory::getUpdateTimestamp))
            .toList();
    }

    private ProductHistory saveProductHistory(Product product, ProductStatusUpdateDto productStatusUpdate) {
        ProductHistory productHistory = new ProductHistory();
        productHistory.setProduct(product);
        productHistory.setStatus(productStatusUpdate.getStatus());
        productHistory.setCurrentPostalCode(productStatusUpdate.getCurrentPostalCode());
        productHistory.setUpdateTimestamp(LocalDateTime.now());

        log.info("New history for product {} saved with status: {}", product.getId(), productStatusUpdate.getStatus());
        return this.productHistoryRepository.save(productHistory);
    }
}