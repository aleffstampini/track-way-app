package br.com.trackwayapp.util;

import br.com.trackwayapp.domain.Address;

public interface FreightCalculator {
    double calculateFreightValue(double weight, Address destination);
}