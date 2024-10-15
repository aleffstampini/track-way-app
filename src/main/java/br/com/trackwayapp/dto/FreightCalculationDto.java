package br.com.trackwayapp.dto;

import lombok.Data;

@Data
public class FreightCalculationDto {
    private Long productId;
    private double freightValue;
    private String estimatedDeliveryDate;
}