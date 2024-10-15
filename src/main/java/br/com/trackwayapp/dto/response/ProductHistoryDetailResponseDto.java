package br.com.trackwayapp.dto.response;

import br.com.trackwayapp.dto.ProductCompleteDto;

import java.util.ArrayList;
import java.util.Collections;

public class ProductHistoryDetailResponseDto extends DefaultPageableResponseDto<ProductCompleteDto> {

    public ProductHistoryDetailResponseDto(ProductCompleteDto historiesWithDetails) {
        this.content = Collections.singletonList(historiesWithDetails);
        this.refreshPageable();
    }

    public ProductHistoryDetailResponseDto() {
        this.content = new ArrayList<>();
        this.refreshPageable();
    }
}