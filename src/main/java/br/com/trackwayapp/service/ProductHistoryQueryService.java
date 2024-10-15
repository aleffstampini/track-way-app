package br.com.trackwayapp.service;

import br.com.trackwayapp.domain.Product;
import br.com.trackwayapp.domain.ProductHistory;
import br.com.trackwayapp.dto.ProductHistoryDto;
import br.com.trackwayapp.dto.ProductWithoutDetailsDto;
import br.com.trackwayapp.dto.response.ProductHistoryResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductHistoryQueryService {

    private final ProductLookupService productLookupService;

    public ProductHistoryResponseDto getProductHistory(Long productId) {
        Product product = this.productLookupService.getProductById(productId);
        List<ProductHistory> histories = getHistoriesByProductId(productId);

        List<ProductHistoryDto> finalHistories = histories.stream()
            .map(ProductHistoryDto::new)
            .collect(Collectors.toList());

        ProductWithoutDetailsDto productWithoutDetailsDto = new ProductWithoutDetailsDto(product);
        productWithoutDetailsDto.setHistories(finalHistories);
        productWithoutDetailsDto.setCurrentPostalCode(this.getMostRecentPostalCode(finalHistories));

        return new ProductHistoryResponseDto(productWithoutDetailsDto);
    }

    private List<ProductHistory> getHistoriesByProductId(Long productId) {
        Product product = this.productLookupService.getProductById(productId);

        return product.getHistory().stream()
            .sorted(Comparator.comparing(ProductHistory::getUpdateTimestamp))
            .toList();
    }

    private String getMostRecentPostalCode(List<ProductHistoryDto> histories) {
        return histories.stream()
            .max(Comparator.comparing(ProductHistoryDto::getUpdateTimestamp))
            .map(ProductHistoryDto::getPostalCode)
            .orElse(null);
    }
}