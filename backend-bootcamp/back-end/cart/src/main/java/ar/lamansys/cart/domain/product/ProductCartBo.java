package ar.lamansys.cart.domain.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class ProductCartBo {
    private String name;
    private String description;
    private Integer quantity;
    private double Price;
    private Long id;
}
