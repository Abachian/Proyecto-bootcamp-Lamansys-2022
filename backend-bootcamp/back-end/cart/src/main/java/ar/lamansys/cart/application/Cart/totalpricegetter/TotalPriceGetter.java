package ar.lamansys.cart.application.Cart.totalpricegetter;

import ar.lamansys.cart.application.Cart.productdeleter.exception.CodeProductDeleterException;
import ar.lamansys.cart.application.Cart.productdeleter.exception.ProductDeleterException;
import ar.lamansys.cart.application.Cart.totalpricegetter.exception.CodeTotalPriceGetteException;
import ar.lamansys.cart.application.Cart.totalpricegetter.exception.TotalPriceGetteException;
import ar.lamansys.cart.application.ports.CartStorage;
import ar.lamansys.cart.application.ports.ProductStockStorage;
import ar.lamansys.cart.domain.cart.CartBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class TotalPriceGetter {
    @Autowired
    CartStorage cartStorage;
    @Autowired
    ProductStockStorage productStockStorage;

    public double run (String cartId ){
        Long idCart= Long.valueOf(cartId);
        validateCart(idCart);
         double totalPrice=0.0;
        var cartProducts= cartStorage.findById(idCart).get().getCartProducts();
        for (Map.Entry<Long,Integer> product : cartProducts.entrySet()) {
            totalPrice+= productStockStorage.getPrice(product.getKey()) * product.getValue();
        }
        return totalPrice;
    }
    private void validateCart(Long id) {
        if (cartStorage.findById(id).isEmpty()) {
            throw new TotalPriceGetteException(CodeTotalPriceGetteException.CART_ID_NOT_FOUND,
                    "the cart with given id don´t exist");
        }
    }
}
