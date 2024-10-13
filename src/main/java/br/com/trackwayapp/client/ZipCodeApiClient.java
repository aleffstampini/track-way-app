package br.com.trackwayapp.client;

import br.com.trackwayapp.config.PropertiesConfiguration;
import br.com.trackwayapp.dto.response.ZipCodeDetailsResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class ZipCodeApiClient {

    private final RestTemplate restTemplate;

    private final PropertiesConfiguration propertiesConfiguration;

    public ZipCodeDetailsResponseDto fetchZipCodeDetails(String zipCode) {
        StringBuilder finalUrl = new StringBuilder();
        finalUrl.append(this.propertiesConfiguration.getZipCodeApiBaseUrl());
        finalUrl.append(zipCode);

        ZipCodeDetailsResponseDto response = new ZipCodeDetailsResponseDto();

        try {
            ResponseEntity<ZipCodeDetailsResponseDto> responseEntity = this.restTemplate.exchange(
                finalUrl.toString(),
                HttpMethod.GET,
                null,
                ZipCodeDetailsResponseDto.class
            );

            response = responseEntity.getBody();
        } catch (Exception e) {
            log.error("Error to fetch zip code info in client", e);
        }

        return response;
    }
}