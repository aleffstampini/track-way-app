package br.com.trackwayapp.listener;

import br.com.trackwayapp.domain.Product;
import br.com.trackwayapp.event.ProductCreatedEvent;
import br.com.trackwayapp.service.FreightCalculationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class ProductEventListener {

    private final FreightCalculationService freightCalculationService;

    @EventListener
    public void handleProductCreated(ProductCreatedEvent event) {
        Product product = event.product();
        this.freightCalculationService.calculateFreight(product);
    }
}