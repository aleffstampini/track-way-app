package br.com.trackwayapp.client;

import br.com.trackwayapp.config.PropertiesConfiguration;
import br.com.trackwayapp.dto.response.PostalCodeDetailsResponseDto;
import br.com.trackwayapp.exception.PostalCodeNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostalCodeApiClient {

    private final RestTemplate restTemplate;
    private final PropertiesConfiguration propertiesConfiguration;

    public PostalCodeDetailsResponseDto fetchPostalCodeDetails(String zipCode) {
        StringBuilder finalUrl = new StringBuilder();
        finalUrl.append(this.propertiesConfiguration.getPostalCodeApiBaseUrl());
        finalUrl.append(zipCode);

        try {
            ResponseEntity<PostalCodeDetailsResponseDto> responseEntity = this.restTemplate.getForEntity(
                finalUrl.toString(), PostalCodeDetailsResponseDto.class);

            return this.handleResponse(responseEntity);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().value() == 404) {
                String messageError = "Zip code not found: " + zipCode;

                log.error(messageError);
                throw new PostalCodeNotFoundException(messageError);
            } else {
                log.error("Unexpected error fetching zip code details: {}", e.getMessage());
                throw new RuntimeException("Unexpected error: " + e.getMessage());
            }
        }
    }

    private PostalCodeDetailsResponseDto handleResponse(ResponseEntity<PostalCodeDetailsResponseDto> responseEntity) {
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        }

        log.error("Unexpected status code: {}", responseEntity.getStatusCode());
        throw new RuntimeException("Unexpected error while fetching zip code details.");
    }
}