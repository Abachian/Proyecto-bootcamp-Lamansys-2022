package ar.lamansys.cart.infrastructure.output.repository.product.database.entity;

import ar.lamansys.cart.infrastructure.output.repository.cart.database.entity.CartJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name= "product_in_cart")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCartJpaEntity {

    @EmbeddedId
    private ProductCartKey id;

    @ManyToOne
    @MapsId("cartId")
    @JoinColumn(name= "cartId")
     CartJpaEntity cart;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name= "productId")
     ProductStockJpaEntity product;

     Integer quantity;

}
