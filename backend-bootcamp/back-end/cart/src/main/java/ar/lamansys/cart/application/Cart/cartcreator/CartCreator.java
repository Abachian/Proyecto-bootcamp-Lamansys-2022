package ar.lamansys.cart.application.Cart.cartcreator;

import ar.lamansys.cart.application.Cart.cartcreator.exception.CartCreatorException;
import ar.lamansys.cart.application.Cart.cartcreator.exception.CodeCartCreatorException;
import ar.lamansys.cart.application.ports.CartStorage;
import ar.lamansys.cart.application.ports.ProductStockStorage;
import ar.lamansys.cart.domain.cart.CartBo;
import ar.lamansys.cart.domain.product.ProductCartBo;
import ar.lamansys.cart.domain.product.ProductStockBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartCreator {
    @Autowired
     CartStorage cartStorage;
    @Autowired
     ProductStockStorage productStockStorage;


    public Long run( CartBo cart, ProductCartBo product){
        validateId(cart.getId());
        validateProduct(product);
        cart.getCartProducts().put(product.getId(),product.getQuantity());
        cartStorage.save(cart);
        return cart.getId();
    }

    private void validateProduct(ProductCartBo product){
        if(productStockStorage.findById( product.getId() ).isEmpty())
            throw new CartCreatorException(CodeCartCreatorException.PRODUCT_ID_NOT_FOUND,
                    "product id" + product.getId() + "dont_found");

        Optional<ProductStockBo> productST= productStockStorage.findById(product.getId());
        if(product.getQuantity().equals(0)){
            throw new CartCreatorException(CodeCartCreatorException.CART_CANNOT_BE_EMPTY, "the cart " +
                    "required at least one product");
        }
        else{
                if (productST.get().getQuantity() < product.getQuantity()) {
                    throw new CartCreatorException(CodeCartCreatorException.PRODUCT_QUANTITY_HIGHER_THAN_STOCK,
                            "invalid product quantity, is higher than the stock quantity ");

                }
            }

    }
    private void validateId(Long id){
        if( ! cartStorage.findById( id ).isEmpty()) {
            throw new CartCreatorException(CodeCartCreatorException.CART_ID_ALREADY_CREATED,
                    "this cart id already exist");
        }
    }
}
