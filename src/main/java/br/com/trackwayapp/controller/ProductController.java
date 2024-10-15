package br.com.trackwayapp.controller;

import br.com.trackwayapp.dto.response.ProductHistoryDetailResponseDto;
import br.com.trackwayapp.dto.response.ProductHistoryResponseDto;
import br.com.trackwayapp.service.ProductHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductHistoryService productHistoryService;

    @GetMapping("/{productId}/history")
    public ResponseEntity<ProductHistoryResponseDto> getHistory(@PathVariable(name = "productId") Long productId) {
        return ResponseEntity.ok(this.productHistoryService.getProductHistory(productId));
    }

    @GetMapping("/{productId}/history/details")
    public ResponseEntity<ProductHistoryDetailResponseDto> getHistoryDetails(
            @PathVariable(name = "productId") Long productId) {
        return ResponseEntity.ok(this.productHistoryService.getProductHistoryDetails(productId));
    }
}