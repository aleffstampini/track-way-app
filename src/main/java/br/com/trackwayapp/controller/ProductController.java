package br.com.trackwayapp.controller;

import br.com.trackwayapp.domain.ProductLocation;
import br.com.trackwayapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{productId}/history")
    public ResponseEntity<List<ProductLocation>> getHistory(@PathVariable Long productId) {
        return ResponseEntity.ok(this.productService.getProductHistoryLocation(productId));
    }
}