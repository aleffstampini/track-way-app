package br.com.trackwayapp.event;

import br.com.trackwayapp.domain.Product;

public record ProductCreatedEvent(Product product) {
}