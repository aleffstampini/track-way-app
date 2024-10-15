package br.com.trackwayapp.repository;

import br.com.trackwayapp.domain.ProductDetails;
import br.com.trackwayapp.domain.ProductHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Long> {

    ProductDetails findByProductHistory(ProductHistory productHistory);

}