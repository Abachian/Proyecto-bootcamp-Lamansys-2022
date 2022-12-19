package ar.lamansys.cart.infrastructure.output.repository.cart.database.mapper;

import ar.lamansys.cart.domain.cart.CartBo;
import ar.lamansys.cart.infrastructure.output.repository.cart.database.entity.CartJpaEntity;
import ar.lamansys.cart.infrastructure.output.repository.product.database.entity.ProductCartJpaEntity;
import ar.lamansys.cart.infrastructure.output.repository.product.database.entity.ProductStockJpaEntity;

import java.util.HashMap;
import java.util.stream.Collectors;
// Como el CartJpaEntity utiliza un Set para almacenar los productos
//y no un HashMap no puedo utilizar el CartAdapter generado en el input.rest

public class CartJpaMapper {

    public static CartBo toCartBo(CartJpaEntity cart) {
        HashMap<Long, Integer> productsBo = new HashMap<>();
        for (ProductCartJpaEntity productJpa : cart.getProducts()) {
            ProductStockJpaEntity productInCart= productJpa.getProduct();
            productsBo.put(productInCart.getId(), productJpa.getQuantity());
        }
        return new CartBo(cart.getId(),productsBo, cart.isState());
    }


}
