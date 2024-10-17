package br.com.trackwayapp.service;

import br.com.trackwayapp.domain.Product;
import br.com.trackwayapp.domain.ProductDetails;
import br.com.trackwayapp.domain.ProductHistory;
import br.com.trackwayapp.dto.ProductCompleteDto;
import br.com.trackwayapp.dto.ProductDetailsDto;
import br.com.trackwayapp.dto.ProductHistoryDto;
import br.com.trackwayapp.dto.ProductHistoryWithDetailsDto;
import br.com.trackwayapp.dto.ProductWithoutDetailsDto;
import br.com.trackwayapp.dto.response.ProductHistoryDetailResponseDto;
import br.com.trackwayapp.dto.response.ProductHistoryResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductHistoryService {

    private final ProductLookupService productLookupService;
    private final ProductDetailsService productDetailsService;

    public ProductHistoryResponseDto getProductHistory(Long productId) {
        Product product = this.productLookupService.getProductById(productId);
        List<ProductHistory> histories = this.getHistoriesByProductId(productId);

        List<ProductHistoryDto> finalHistories = histories.stream()
            .map(ProductHistoryDto::new)
            .collect(Collectors.toList());

        ProductWithoutDetailsDto productWithoutDetailsDto = new ProductWithoutDetailsDto(product);
        productWithoutDetailsDto.setHistories(finalHistories);

        String mostRecentPostalCode = finalHistories.stream()
            .max(Comparator.comparing(ProductHistoryDto::getUpdateTimestamp))
            .map(ProductHistoryDto::getPostalCode)
            .orElse(null);

        productWithoutDetailsDto.setCurrentPostalCode(mostRecentPostalCode);

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

        String mostRecentPostalCode = finalHistories.stream()
            .max(Comparator.comparing(ProductHistoryWithDetailsDto::getUpdateTimestamp))
            .map(ProductHistoryWithDetailsDto::getPostalCode)
            .orElse(null);

        productCompleteDto.setCurrentPostalCode(mostRecentPostalCode);

        return new ProductHistoryDetailResponseDto(productCompleteDto);
    }

    private List<ProductHistory> getHistoriesByProductId(Long productId) {
        Product product = this.productLookupService.getProductById(productId);

        return product.getHistory().stream()
            .sorted(Comparator.comparing(ProductHistory::getUpdateTimestamp))
            .toList();
    }
}