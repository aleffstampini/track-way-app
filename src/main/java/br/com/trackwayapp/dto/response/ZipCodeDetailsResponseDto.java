package br.com.trackwayapp.dto.response;

import lombok.Data;

@Data
public class ZipCodeDetailsResponseDto {
    private String zipCode;
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private double latitude;
    private double longitude;
}