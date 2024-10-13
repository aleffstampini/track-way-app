package br.com.trackwayapp.service;

import br.com.trackwayapp.client.ZipCodeApiClient;
import br.com.trackwayapp.dto.response.ZipCodeDetailsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ZipCodeService {

    private final ZipCodeApiClient zipCodeApiClient;

    public ZipCodeDetailsResponseDto getZipCodeDetails(String zipCode) {
        return this.zipCodeApiClient.fetchZipCodeDetails(zipCode);
    }
}