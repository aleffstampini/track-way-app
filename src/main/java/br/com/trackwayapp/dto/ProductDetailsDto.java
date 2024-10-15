package br.com.trackwayapp.dto;

import br.com.trackwayapp.domain.ProductDetails;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductDetailsDto {

    private Long id;
    private LocalDateTime queryTimestamp;
    private String apiResponse;

    public ProductDetailsDto(ProductDetails details) {
        if (details != null) {
            this.id = details.getId();
            this.queryTimestamp = details.getQueryTimestamp();
            this.apiResponse = details.getApiResponse();
        }
    }
}