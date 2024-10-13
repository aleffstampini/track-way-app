package br.com.trackwayapp.controller;

import br.com.trackwayapp.dto.response.DefaultPageableResponseDto;
import br.com.trackwayapp.dto.response.ZipCodeDetailsResponseDto;
import br.com.trackwayapp.dto.response.ZipCodeResponseDto;
import br.com.trackwayapp.service.ZipCodeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class ZipCodeController {

    private final ZipCodeService zipCodeService;

    @GetMapping("/{zipCode}")
    public ResponseEntity<DefaultPageableResponseDto<ZipCodeDetailsResponseDto>> getZipCodeDetails(
            @PathVariable String zipCode) {
        ZipCodeDetailsResponseDto zipCodeDetails = this.zipCodeService.getZipCodeDetails(zipCode);
        return ResponseEntity.ok(new ZipCodeResponseDto(zipCodeDetails));
    }
}