package br.com.trackwayapp.service;

import br.com.trackwayapp.domain.Product;
import br.com.trackwayapp.domain.ProductHistory;
import br.com.trackwayapp.dto.ProductDto;
import br.com.trackwayapp.dto.response.PostalCodeDetailsResponseDto;
import br.com.trackwayapp.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "add-product", groupId = "group_id")
    public void addProduct(String message) throws JsonProcessingException {
        ProductDto productDto = this.objectMapper.readValue(message, ProductDto.class);

        Product product = new Product();
        product.setName(productDto.getName());
        product.setDestinationAddress(productDto.getDestinationAddress());

        Product savedProduct = this.productRepository.save(product);

        ProductHistory savedProductHistory = this.productHistoryService.saveProductHistory(
            savedProduct, productDto.getCurrentPostalCode());

        PostalCodeDetailsResponseDto postalCodeDetailsResponse = this.postalCodeService.getPostalCodeDetails(savedProductHistory);

        this.productDetailsService.saveProductDetails(savedProductHistory, postalCodeDetailsResponse);
    }
}