package br.com.trackwayapp.domain;

import br.com.trackwayapp.enums.ProductHistoryEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_history")
@NoArgsConstructor
@Data
public class ProductHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_history_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProductHistoryEnum status;

    @Column(name = "current_postal_code")
    private String currentPostalCode;

    @Column(name = "update_timestamp")
    private LocalDateTime updateTimestamp;

}