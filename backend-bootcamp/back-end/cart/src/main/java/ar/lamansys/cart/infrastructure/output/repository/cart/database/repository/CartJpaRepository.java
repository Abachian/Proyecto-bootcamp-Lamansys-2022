package ar.lamansys.cart.infrastructure.output.repository.cart.database.repository;

import ar.lamansys.cart.infrastructure.output.repository.cart.database.entity.CartJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartJpaRepository extends JpaRepository<CartJpaEntity,Long> {

    @Query("from CartJpaEntity c where c.id = :id")
    Optional<CartJpaEntity> findByIdJpa(@Param("id") Long id);
}
