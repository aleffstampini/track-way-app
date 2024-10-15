package br.com.trackwayapp.dto;


import br.com.trackwayapp.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ProductWithoutDetailsDto extends ProductDto {

    private List<ProductHistoryDto> histories;

    public ProductWithoutDetailsDto(Product product) {
        this.setId(product.getId());
        this.setName(product.getName());
        this.setDestinationAddress(product.getDestinationAddress());
        this.setWeight(product.getWeight());
    }
}