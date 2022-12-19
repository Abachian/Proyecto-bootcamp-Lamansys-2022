package ar.lamansys.cart.application.Cart.cartcheckout;

import ar.lamansys.cart.application.Cart.cartcheckout.exception.CartCheckoutException;
import ar.lamansys.cart.application.Cart.cartcheckout.exception.CodeCartCheckoutException;
import ar.lamansys.cart.application.Cart.productdeleter.exception.CodeProductDeleterException;
import ar.lamansys.cart.application.Cart.productdeleter.exception.ProductDeleterException;
import ar.lamansys.cart.application.Cart.totalpricegetter.exception.CodeTotalPriceGetteException;
import ar.lamansys.cart.application.Cart.totalpricegetter.exception.TotalPriceGetteException;
import ar.lamansys.cart.application.ports.CartStorage;
import ar.lamansys.cart.application.ports.ProductStockStorage;
import ar.lamansys.cart.domain.cart.CartBo;
import ar.lamansys.cart.domain.exception.DomainException;
import ar.lamansys.cart.domain.product.ProductStockBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class CartCheckout {
    @Autowired
    CartStorage cartStorage;
    @Autowired
    ProductStockStorage productStockStorage;


    public CartBo run (String cartId){
        validateCart( cartId );
        Optional<CartBo> cartChecked= cartStorage.findById(Long.valueOf(cartId));
        if (!cartChecked.get().isState()) {
            for (Map.Entry<Long, Integer> product : cartChecked.get().getCartProducts().entrySet()) {
                Optional<ProductStockBo> productInStock = productStockStorage.findById(product.getKey());
                if (productInStock.get().getQuantity() >= product.getValue()) {
                    productStockStorage.discountQuantity(product.getKey(), product.getValue());

                } else {
                    throw new CartCheckoutException(CodeCartCheckoutException.PRODUCT_QUANTITY_HIGHER_THAN_STOCK,
                                        "the product quantity of" + product.getKey()+ "is higher than the stock");
                }
            }
            cartChecked.get().setState(true);
            cartStorage.save(cartChecked.get());
            return cartChecked.get();
        }
        else {
            throw new CartCheckoutException(CodeCartCheckoutException.CART_ALREADY_SOLD,
                                "the cart with given id is already sold");
        }

    }
    private void validateCart(String cartId) {
        if (cartStorage.findById(Long.valueOf(cartId)).isEmpty()) {
            throw new CartCheckoutException(CodeCartCheckoutException.CART_ID_NOT_FOUND,
                    "the cart with given id don´t exist");
        }
    }
}
