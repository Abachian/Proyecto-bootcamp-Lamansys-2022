package ar.lamansys.cart.infrastructure.input.rest.cart.dto.response;

import ar.lamansys.cart.infrastructure.input.rest.productcart.dto.request.ProductCartDTO;
import lombok.*;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartWithIdDTO {
    private Long id;
    private HashMap<Long, Integer> cartProducts;

}
