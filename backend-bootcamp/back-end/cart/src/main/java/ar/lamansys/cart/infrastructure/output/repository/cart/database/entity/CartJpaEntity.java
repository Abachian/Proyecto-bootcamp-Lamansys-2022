package ar.lamansys.cart.infrastructure.output.repository.cart.database.entity;

import ar.lamansys.cart.domain.cart.CartBo;
import ar.lamansys.cart.infrastructure.output.repository.product.database.entity.ProductCartJpaEntity;
import ar.lamansys.cart.infrastructure.output.repository.product.database.entity.ProductStockJpaEntity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "cart")
@NoArgsConstructor
public class CartJpaEntity {

    @Id
    private Long id;

    @Column
    private boolean state;
    @OneToMany(mappedBy = "cart")
    Set<ProductCartJpaEntity> products;


    public CartJpaEntity(Long id, boolean state) {
        this.id = id;
        this.state = state;
        this.products = new HashSet<>();
    }


}