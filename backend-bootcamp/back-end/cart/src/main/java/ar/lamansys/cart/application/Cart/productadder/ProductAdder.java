package ar.lamansys.cart.application.Cart.productadder;

import ar.lamansys.cart.application.Cart.productadder.exception.ProductAdderException;
import ar.lamansys.cart.application.Cart.productadder.exception.CodeProductAdderException;
import ar.lamansys.cart.application.ports.CartStorage;
import ar.lamansys.cart.application.ports.ProductStockStorage;
import ar.lamansys.cart.domain.cart.CartBo;
import ar.lamansys.cart.domain.product.ProductStockBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductAdder {
    @Autowired
    CartStorage cartStorage;
    @Autowired
    ProductStockStorage productStockStorage;

    public Optional<CartBo> run (String cartId, Long productId, Integer quantity ){
        Long idCart= Long.valueOf(cartId);
        validateCart(idCart);
        validateProduct(productId, quantity);
        return cartStorage.findById( idCart ).map
                (cartBo -> {
                    if(!cartBo.isState()) {
                        if (cartBo.getCartProducts().containsKey(productId)) {
                            if (cartBo.getCartProducts().get(productId) + quantity <
                                    productStockStorage.findById(productId).get().getQuantity()) {

                                cartBo.addProduct(productId, quantity);
                                cartStorage.save(cartBo);
                                return cartBo;
                            } else {
                                throw new ProductAdderException(CodeProductAdderException.PRODUCT_QUANTITY_HIGHER_THAN_STOCK,
                                        "invalid product quantity, is higher than the stock quantity");
                            }
                        } else {
                            cartBo.addProduct(productId, quantity);
                            cartStorage.save(cartBo);
                            return cartBo;
                        }
                    }
                    else{
                        throw new ProductAdderException(CodeProductAdderException.CART_SOLD,
                                "the cart with given id is already sold");
                    }
                });
    }

    private void validateProduct(Long productId, Integer quantity){
        if( productStockStorage.findById(  productId  ).isEmpty() )
            throw new ProductAdderException(CodeProductAdderException.PRODUCT_ID_NOT_FOUND,
                    "product id" + productId + "dont found");

        Optional<ProductStockBo> productST= productStockStorage.findById( productId  );
        if(quantity.equals(0)){
            throw new ProductAdderException(CodeProductAdderException.PRODUCT_QUANTITY_NULL, "the product quantity " +
                    "cannot be cero");
        }
        else{
            if (productST.get().getQuantity() < quantity) {
                throw new ProductAdderException(CodeProductAdderException.PRODUCT_QUANTITY_HIGHER_THAN_STOCK,
                        "invalid product quantity, is higher than the stock quantity ");

            }

        }

    }

    private void validateCart(Long id) {
        if (cartStorage.findById(id).isEmpty()) {
            throw new ProductAdderException(CodeProductAdderException.CART_ID_NOT_FOUND,
                    "the cart with given id don´t exist");
        }
    }
}
