package ar.lamansys.cart.infrastructure.input.rest.productcart.dto.request;


import ar.lamansys.cart.domain.product.ProductStockBo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCartDTO {
    private String name;
    private String description;
    private double price;
    private Integer quantity;
    private Long id;

}
