package br.com.trackwayapp.service;

import br.com.trackwayapp.client.PostalCodeApiClient;
import br.com.trackwayapp.domain.ProductHistory;
import br.com.trackwayapp.dto.response.PostalCodeDetailsResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostalCodeService {

    private final PostalCodeApiClient postalCodeApiClient;

    public PostalCodeDetailsResponseDto getPostalCodeDetails(ProductHistory productHistory) {
        return this.postalCodeApiClient.fetchPostalCodeDetails(productHistory.getCurrentPostalCode());
    }
}