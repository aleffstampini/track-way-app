package br.com.trackwayapp.dto;

import lombok.Data;

@Data
public class ProductStatusUpdateDto {
    private Long productId;
    private String status;
    private String currentLocation;
}