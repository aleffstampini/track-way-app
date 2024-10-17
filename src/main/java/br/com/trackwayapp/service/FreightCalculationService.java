package br.com.trackwayapp.service;

import br.com.trackwayapp.domain.Product;
import br.com.trackwayapp.util.FreightCalculator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class FreightCalculationService {

    private final ProductService productService;
    private final FreightCalculator freightCalculator;

    public void calculateFreight(Product product) {
        log.info("Calculating freight value and estimated delivery date for product: {}", product.getId());
        double freightValue = this.freightCalculator.calculateFreightValue(product.getWeight(), product.getDestinationAddress());
        String estimatedDeliveryDate = LocalDateTime.now().plusDays(7).toString();

        product.setFreightValue(freightValue);
        product.setEstimatedDeliveryDate(estimatedDeliveryDate);

        log.info("Saving freight value and estimated delivery date for product: {}", product.getId());
        this.productService.updateProduct(product);
    }
}