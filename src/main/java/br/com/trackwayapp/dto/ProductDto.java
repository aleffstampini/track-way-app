package br.com.trackwayapp.dto;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String currentPostalCode;
    private String destinationAddress;
}