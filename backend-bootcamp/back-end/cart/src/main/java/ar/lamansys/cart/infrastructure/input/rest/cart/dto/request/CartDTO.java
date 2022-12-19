package ar.lamansys.cart.infrastructure.input.rest.cart.dto.request;

import ar.lamansys.cart.infrastructure.input.rest.productcart.dto.request.ProductCartDTO;
import lombok.*;

import java.util.HashMap;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDTO {
    private HashMap<Long, Integer> cartProducts;

}
