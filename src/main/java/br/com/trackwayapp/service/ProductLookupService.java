package br.com.trackwayapp.service;

import br.com.trackwayapp.domain.Product;
import br.com.trackwayapp.dto.response.ProductResponseDto;
import br.com.trackwayapp.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductLookupService {

    private final ProductRepository productRepository;

    private static final int DEFAULT_PAGE_NUMBER = 0;

    public Product getProductById(Long productId) {
        return this.productRepository.findById(productId)
            .orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    public ProductResponseDto getAllProducts(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Product> products = this.productRepository.findAll(pageRequest);

        return new ProductResponseDto(products);
    }
}