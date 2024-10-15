package br.com.trackwayapp.service;

import br.com.trackwayapp.domain.ProductDetails;
import br.com.trackwayapp.domain.ProductHistory;
import br.com.trackwayapp.dto.response.PostalCodeDetailsResponseDto;
import br.com.trackwayapp.repository.ProductDetailsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductDetailsService {

    private final ProductDetailsRepository productDetailsRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void saveProductDetails(ProductHistory productHistory, PostalCodeDetailsResponseDto apiResponse) throws JsonProcessingException {
        ProductDetails productDetails = new ProductDetails();
        productDetails.setProductHistory(productHistory);
        productDetails.setQueryTimestamp(LocalDateTime.now());

        String apiResponseJson = apiResponse != null ? objectMapper.writeValueAsString(apiResponse) : "{}";

        productDetails.setApiResponse(apiResponseJson);

        this.productDetailsRepository.save(productDetails);
    }

    public ProductDetails getProductDetailsByProductHistory(ProductHistory productHistory) {
        return this.productDetailsRepository.findByProductHistory(productHistory);
    }
}