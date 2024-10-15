package br.com.trackwayapp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "product_details")
@NoArgsConstructor
@Data
public class ProductDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_details_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_history_id", nullable = false)
    private ProductHistory productHistory;

    @Column(name = "query_timestamp")
    private LocalDateTime queryTimestamp;

    @Column(name = "api_response", columnDefinition = "TEXT")
    private String apiResponse;

}