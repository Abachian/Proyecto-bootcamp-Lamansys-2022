package ar.lamansys.cart.infrastructure.output.repository.product.database.repository;

import ar.lamansys.cart.infrastructure.output.repository.product.database.entity.ProductStockJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductStockJpaRepository extends JpaRepository<ProductStockJpaEntity,Long> {

    Optional<ProductStockJpaEntity> findByName(String name);

}
