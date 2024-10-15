package br.com.trackwayapp.repository;

import br.com.trackwayapp.domain.ProductLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductLocationRepository extends JpaRepository<ProductLocation, Long> {
}