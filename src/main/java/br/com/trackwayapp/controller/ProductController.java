package br.com.trackwayapp.controller;

import br.com.trackwayapp.dto.response.ProductHistoryDetailResponseDto;
import br.com.trackwayapp.dto.response.ProductHistoryResponseDto;
import br.com.trackwayapp.dto.response.ProductResponseDto;
import br.com.trackwayapp.service.ProductHistoryService;
import br.com.trackwayapp.service.ProductLookupService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductLookupService productLookupService;
    private final ProductHistoryService productHistoryService;

    @Operation(summary = "Obter todos os produtos", description = "Retorna uma lista paginada de produtos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ProductResponseDto.class))),
        @ApiResponse(responseCode = "404", description = "Nenhum produto encontrado",
            content = @Content)
    })
    @GetMapping
    public ResponseEntity<ProductResponseDto> getAllProducts(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        return ResponseEntity.ok(this.productLookupService.getAllProducts(page, size));
    }

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