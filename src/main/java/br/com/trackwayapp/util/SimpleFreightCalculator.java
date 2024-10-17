package br.com.trackwayapp.util;

import br.com.trackwayapp.domain.Address;
import org.springframework.stereotype.Component;

@Component
public class SimpleFreightCalculator implements FreightCalculator {
    @Override
    public double calculateFreightValue(double weight, Address destination) {
        double baseRate = 10.0;
        double weightRate = 5.0;

        double locationSurcharge = this.getLocationSurcharge(destination);

        return baseRate + (weight * weightRate) + locationSurcharge;
    }

    private double getLocationSurcharge(Address destination) {
        return switch (destination.getState()) {
            case "SP" -> 15.0;
            case "RJ" -> 20.0;
            case "MG" -> 12.0;
            default -> 25.0;
        };
    }
}