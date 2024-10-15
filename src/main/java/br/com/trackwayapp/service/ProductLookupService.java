package br.com.trackwayapp.service;

import br.com.trackwayapp.domain.Product;
import br.com.trackwayapp.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductLookupService {

    private final ProductRepository productRepository;

    private static final int DEFAULT_PAGE_NUMBER = 0;

    public Product getProductById(Long productId) {
        return this.productRepository.findById(productId)
            .orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    public Page<Product> getAllProducts(PageRequest pageRequest) {
        return this.productRepository.findAll(pageRequest);
    }
}