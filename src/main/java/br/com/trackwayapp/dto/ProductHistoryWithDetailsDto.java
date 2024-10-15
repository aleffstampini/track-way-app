package br.com.trackwayapp.dto;

import br.com.trackwayapp.domain.ProductHistory;
import br.com.trackwayapp.enums.ProductHistoryEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductHistoryWithDetailsDto {

    private Long id;
    private ProductHistoryEnum status;
    private String postalCode;
    private LocalDateTime updateTimestamp;
    private ProductDetailsDto details;

    public ProductHistoryWithDetailsDto(ProductHistory history) {
        this.id = history.getId();
        this.status = history.getStatus();
        this.postalCode = history.getPostalCode();
        this.updateTimestamp = history.getUpdateTimestamp();
    }
}