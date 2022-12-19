package ar.lamansys.cart.infrastructure.output.repository.product.database.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@EqualsAndHashCode
@Getter
@Setter
public class ProductCartKey implements Serializable {

    @Column(name = "cartId")
    Long cartId;
    @Column(name ="productId")
    Long productId;

}
