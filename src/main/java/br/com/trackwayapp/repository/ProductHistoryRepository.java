package br.com.trackwayapp.repository;

import br.com.trackwayapp.domain.Product;
import br.com.trackwayapp.domain.ProductHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductHistoryRepository extends JpaRepository<ProductHistory, Long> {

    void findByProduct(Product product);

}