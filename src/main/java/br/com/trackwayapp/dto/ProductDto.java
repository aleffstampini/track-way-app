package br.com.trackwayapp.dto;

import br.com.trackwayapp.domain.Address;
import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private Double weight;
    private String currentPostalCode;
    private Address destinationAddress;
}