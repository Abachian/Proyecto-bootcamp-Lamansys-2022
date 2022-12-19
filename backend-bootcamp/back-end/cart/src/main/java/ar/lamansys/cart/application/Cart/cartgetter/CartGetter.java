package ar.lamansys.cart.application.Cart.cartgetter;

import ar.lamansys.cart.application.ports.CartStorage;
import ar.lamansys.cart.domain.cart.CartBo;
import ar.lamansys.cart.domain.exception.DomainException;
import org.springframework.stereotype.Service;


@Service
public class CartGetter {
    private final CartStorage cartStorage;

    public CartGetter (CartStorage cartRepository){
        this.cartStorage= cartRepository;
    }

    public CartBo run (String id){
        return cartStorage
                .findById( Long.valueOf(id) )
                .orElseThrow(()-> new DomainException("cart_id_not_found", "cart_with_given_id_doesn´t_exist") );
    }
}
