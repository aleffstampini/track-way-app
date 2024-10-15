package br.com.trackwayapp.dto.response;

import br.com.trackwayapp.dto.ProductWithoutDetailsDto;

import java.util.ArrayList;
import java.util.Collections;

public class ProductHistoryResponseDto extends DefaultPageableResponseDto<ProductWithoutDetailsDto> {

    public ProductHistoryResponseDto(ProductWithoutDetailsDto productWithoutDetails) {
        this.content = Collections.singletonList(productWithoutDetails);
        this.refreshPageable();
    }

    public ProductHistoryResponseDto() {
        this.content = new ArrayList<>();
        this.refreshPageable();
    }
}