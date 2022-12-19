package ar.lamansys.cart.infrastructure.output.repository.product.database.repository;

import ar.lamansys.cart.infrastructure.output.repository.product.database.entity.ProductCartJpaEntity;
import ar.lamansys.cart.infrastructure.output.repository.product.database.entity.ProductCartKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ProductCartJpaRepository extends JpaRepository<ProductCartJpaEntity, ProductCartKey> {
}
