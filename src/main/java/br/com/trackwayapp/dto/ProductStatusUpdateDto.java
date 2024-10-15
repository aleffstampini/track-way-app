package br.com.trackwayapp.dto;

import br.com.trackwayapp.enums.ProductHistoryEnum;
import lombok.Data;

@Data
public class ProductStatusUpdateDto {
    private Long productId;
    private ProductHistoryEnum status;
    private String currentPostalCode;
}