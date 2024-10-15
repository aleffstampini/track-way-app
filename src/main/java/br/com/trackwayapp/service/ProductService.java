package br.com.trackwayapp.service;

import br.com.trackwayapp.domain.Product;
import br.com.trackwayapp.domain.ProductHistory;
import br.com.trackwayapp.dto.ProductDto;
import br.com.trackwayapp.dto.response.PostalCodeDetailsResponseDto;
import br.com.trackwayapp.event.ProductCreatedEvent;
import br.com.trackwayapp.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductHistoryService productHistoryService;
    private final ProductDetailsService productDetailsService;
    private final PostalCodeService postalCodeService;

    private final ApplicationEventPublisher eventPublisher;

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "add-product", groupId = "group_id")
    public void addProduct(String message) {
        try {
            ProductDto productDto = objectMapper.readValue(message, ProductDto.class);
            Product product = this.createProductFromDto(productDto);
            Product savedProduct = this.productRepository.save(product);

            this.handleProductHistory(savedProduct, productDto);

            this.eventPublisher.publishEvent(new ProductCreatedEvent(savedProduct));
        } catch (JsonProcessingException e) {
            log.error("Error processing product message: {}", message, e);
        }
    }

    private Product createProductFromDto(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDestinationAddress(productDto.getDestinationAddress());
        product.setWeight(productDto.getWeight());

        return product;
    }

    private void handleProductHistory(Product savedProduct, ProductDto productDto) {
        ProductHistory savedProductHistory = productHistoryService.saveProductHistory(
            savedProduct, productDto.getCurrentPostalCode());

        PostalCodeDetailsResponseDto postalCodeDetailsResponse = this.postalCodeService
            .getPostalCodeDetails(savedProductHistory);

        this.productDetailsService.saveProductDetails(savedProductHistory, postalCodeDetailsResponse);
    }

    public void updateProduct(Product product) {
        this.productRepository.save(product);
    }
}