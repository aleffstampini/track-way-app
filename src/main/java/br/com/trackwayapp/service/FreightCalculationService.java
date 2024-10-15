package br.com.trackwayapp.service;

import br.com.trackwayapp.domain.Address;
import br.com.trackwayapp.domain.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class FreightCalculationService {

    private final ProductService productService;

    public void calculateFreight(Product product) {
        log.info("Calculate freight value and estimated delivery date to product: {}", product.getId());
        double freightValue = this.calculateFreightValue(product.getWeight(), product.getDestinationAddress());
        String estimatedDeliveryDate = LocalDateTime.now().plusDays(5).toString();

        product.setFreightValue(freightValue);
        product.setEstimatedDeliveryDate(estimatedDeliveryDate);

        log.info("Saving freight value and estimated delivery in product: {}", product.getId());
        this.productService.updateProduct(product);
    }

    private double calculateFreightValue(double weight, Address destination) {
        double baseRate = 10.0;
        double weightRate = 5.0;
        return baseRate + (weight * weightRate);
    }
}