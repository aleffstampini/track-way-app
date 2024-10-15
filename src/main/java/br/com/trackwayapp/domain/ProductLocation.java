package br.com.trackwayapp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Entity(name = "product_location")
@Data
public class ProductLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private String status;  // e.g., "In transit", "Posted", "Delivered", etc.

    private String currentLocation; // e.g., "São Paulo, SP", GPS coordinates, etc.

    private LocalDateTime updateTimestamp;

}