package br.com.trackwayapp.service;

import br.com.trackwayapp.domain.Product;
import br.com.trackwayapp.domain.ProductLocation;
import br.com.trackwayapp.dto.ProductStatusUpdateDto;
import br.com.trackwayapp.repository.ProductLocationRepository;
import br.com.trackwayapp.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductLocationRepository productLocationRepository;

    public Product getProductById(Long productId) {
        return this.productRepository.findById(productId)
            .orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    public List<ProductLocation> getProductHistoryLocation(Long productId) {
        Product product = this.getProductById(productId);

        return product.getLocations().stream()
            .sorted(Comparator.comparing(ProductLocation::getUpdateTimestamp))
            .toList();
    }

    @KafkaListener(topics = "product-status")
    public void updateProductStatus(ProductStatusUpdateDto productStatusUpdate) {
        log.info("Status update received for product: {}",
            productStatusUpdate.getProductId());

        Product product = this.getProductById(productStatusUpdate.getProductId());

        ProductLocation newLocation = new ProductLocation();
        newLocation.setProduct(product);
        newLocation.setStatus(productStatusUpdate.getStatus());
        newLocation.setCurrentZipCode(productStatusUpdate.getCurrentLocation());
        newLocation.setUpdateTimestamp(LocalDateTime.now());

        this.productLocationRepository.save(newLocation);
        log.info("New location for product {} saved with status: {}",
            product.getId(), productStatusUpdate.getStatus());
    }
}