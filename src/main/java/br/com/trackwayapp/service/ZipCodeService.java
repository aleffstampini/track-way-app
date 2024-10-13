package br.com.trackwayapp.service;

import br.com.trackwayapp.client.ZipCodeApiClient;
import br.com.trackwayapp.dto.response.ZipCodeResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ZipCodeService {

    private final ZipCodeApiClient zipCodeApiClient;

    public ZipCodeResponseDto getZipCodeDetails(String zipCode) throws JsonProcessingException {
        return this.zipCodeApiClient.fetchZipCodeDetails(zipCode);
    }
}