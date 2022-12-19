package ar.lamansys.cart.application.Cart.productdeleter;

import ar.lamansys.cart.application.Cart.productadder.exception.CodeProductAdderException;
import ar.lamansys.cart.application.Cart.productadder.exception.ProductAdderException;
import ar.lamansys.cart.application.Cart.productdeleter.exception.CodeProductDeleterException;
import ar.lamansys.cart.application.Cart.productdeleter.exception.ProductDeleterException;
import ar.lamansys.cart.application.ports.CartStorage;
import ar.lamansys.cart.application.ports.ProductStockStorage;
import ar.lamansys.cart.domain.cart.CartBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductDeleter {
    @Autowired
    CartStorage cartStorage;
    @Autowired
    ProductStockStorage productStockStorage;

    public Optional<CartBo> run (String cartId, Long productId, Integer quantity ){
        Long idCart= Long.valueOf(cartId);
        validateCart(idCart);
        validateProduct(productId);
        return cartStorage.findById( idCart ).
                map(cartBo -> {
                    if(!cartBo.isState()) {
                        if (cartBo.getCartProducts().containsKey(productId)) {
                            if (cartBo.getCartProducts().get(productId) >=  quantity ) {
                               cartBo.deleteProduct(productId,quantity);
                                cartStorage.save(cartBo);
                                return cartBo;
                            } else {
                                throw new ProductDeleterException(CodeProductDeleterException.PRODUCT_QUANTITY_HIGHER_THAN_CART,
                                        "invalid product quantity, is higher than the cart quantity");
                            }
                        } else {
                           throw new ProductDeleterException(CodeProductDeleterException.PRODUCT_NOT_FOUND_IN_CART, "the given product" +
                                   "is not found in the cart");
                        }
                    }
                    else{
                        throw new ProductDeleterException(CodeProductDeleterException.CART_SOLD,
                                "the cart with given id is already sold");
                        }
                });
    }

    private void validateProduct(Long productId) {
        if (productStockStorage.findById(productId).isEmpty()) {
            throw new ProductDeleterException(CodeProductDeleterException.PRODUCT_ID_NOT_FOUND,
                    "product id" + productId + "dont found");

        }
    }

    private void validateCart(Long id) {
        if (cartStorage.findById(id).isEmpty()) {
            throw new ProductDeleterException(CodeProductDeleterException.CART_ID_NOT_FOUND,
                    "the cart with given id don´t exist");
        }
    }
}
