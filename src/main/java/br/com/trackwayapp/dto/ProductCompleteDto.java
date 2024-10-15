package br.com.trackwayapp.dto;

import br.com.trackwayapp.domain.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductCompleteDto extends ProductDto {

    private List<ProductHistoryWithDetailsDto> histories;

    public ProductCompleteDto(Product product) {
        this.setId(product.getId());
        this.setName(product.getName());
        this.setDestinationAddress(product.getDestinationAddress());
    }
}