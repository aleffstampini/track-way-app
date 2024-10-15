package br.com.trackwayapp.dto;

import br.com.trackwayapp.domain.ProductHistory;
import br.com.trackwayapp.enums.ProductHistoryEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class ProductHistoryDto {

    private Long id;
    private ProductHistoryEnum status;
    private String postalCode;
    private LocalDateTime updateTimestamp;

    public ProductHistoryDto(ProductHistory history) {
        this.id = history.getId();
        this.status = history.getStatus();
        this.postalCode = history.getPostalCode();
        this.updateTimestamp = history.getUpdateTimestamp();
    }
}