package br.com.trackwayapp.util;

import br.com.trackwayapp.domain.Address;
import org.springframework.stereotype.Component;

@Component
public class SimpleFreightCalculator implements FreightCalculator {
    @Override
    public double calculateFreightValue(double weight, Address destination) {
        double baseRate = 10.0;
        double weightRate = 5.0;
        return baseRate + (weight * weightRate);
    }
}