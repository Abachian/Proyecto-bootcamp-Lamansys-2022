package ar.lamansys.cart.domain.cart;
import ar.lamansys.cart.domain.exception.DomainException;
import ar.lamansys.cart.domain.AutoincrementId;
import ar.lamansys.cart.domain.product.ProductCartBo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CartBo {
    private Long id;
    private HashMap<Long,Integer> cartProducts;
    boolean state;

    public CartBo(Long id, HashMap<Long, Integer> cartProducts, boolean state) {
        validateId(id);
        validateProducts(cartProducts);
        this.id = id;
        this.cartProducts = cartProducts;
        this.state = state;
    }


    public CartBo(HashMap<Long, Integer> cartProducts){
        validateProducts(cartProducts);
        this.id= AutoincrementId.incrementAndGetId();
        this.cartProducts=cartProducts;
        this.state=false;
    }
    public CartBo(Long id, HashMap<Long, Integer> cartProducts) {
        validateProducts(cartProducts);
        this.id = id;
        this.cartProducts = cartProducts;
    }

    public CartBo(String id){
        validateId( Long.valueOf(id) );
        this.id= Long.valueOf(id);
        this.cartProducts=new HashMap<>();
        this.state= false;
    }

    public void addProduct(Long idProduct, Integer quantity) {
        if (cartProducts.containsKey(idProduct)) {
            cartProducts.put(idProduct, cartProducts.get(idProduct) + quantity);
        } else {
            cartProducts.put(idProduct, quantity);
        }
    }
    public void deleteProduct(Long idProduct, Integer quantity){
        if(cartProducts.containsKey(idProduct)){
            Integer acutalQuantity= cartProducts.get(idProduct);
            if(acutalQuantity>= quantity){
                cartProducts.put(idProduct, acutalQuantity-quantity);
            }
            else{
                cartProducts.remove(idProduct);
            }
        }


    }
    public void validateId(Long id) {
        if (id == null) {
            throw new DomainException("cart_id_not_null", "The cart id cannot be null.");
        }
    }
    public void validateProducts(HashMap<Long,Integer> products) {
        if (products == null) {
            throw new DomainException("cart_products_not_null", "The cart products cannot be null.");
        }
    }




}
