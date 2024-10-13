package br.com.trackwayapp.controller;

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
    public ResponseEntity<ZipCodeResponseDto> getZipCodeDetails(@PathVariable String zipCode) throws JsonProcessingException {
        ZipCodeResponseDto response = this.zipCodeService.getZipCodeDetails(zipCode);
        return ResponseEntity.ok(response);
    }
}