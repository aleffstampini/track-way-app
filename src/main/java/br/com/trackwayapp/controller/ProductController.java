package br.com.trackwayapp.controller;

import br.com.trackwayapp.dto.response.ProductHistoryDetailResponseDto;
import br.com.trackwayapp.dto.response.ProductHistoryResponseDto;
import br.com.trackwayapp.service.ProductHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Obter histórico do produto", description = "Retorna o histórico do produto especificado pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Histórico retornado com sucesso",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ProductHistoryResponseDto.class))),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado",
            content = @Content)
    })
    @GetMapping("/{productId}/history")
    public ResponseEntity<ProductHistoryResponseDto> getHistory(@PathVariable(name = "productId") Long productId) {
        return ResponseEntity.ok(this.productHistoryService.getProductHistory(productId));
    }

    @Operation(summary = "Obter histórico detalhado do produto", description = "Retorna o histórico detalhado do produto especificado pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Histórico detalhado retornado com sucesso",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ProductHistoryDetailResponseDto.class))),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado",
            content = @Content)
    })
    @GetMapping("/{productId}/history/details")
    public ResponseEntity<ProductHistoryDetailResponseDto> getHistoryDetails(
            @PathVariable(name = "productId") Long productId) {
        return ResponseEntity.ok(this.productHistoryService.getProductHistoryDetails(productId));
    }
}