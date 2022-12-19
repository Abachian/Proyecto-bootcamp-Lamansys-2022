package ar.lamansys.cart.infrastructure.output.repository.cart.inmemory.entity;

import ar.lamansys.cart.domain.cart.CartBo;
import lombok.Data;

import java.util.HashMap;
@Data
public class CartEntity {
    private Long id;
    HashMap<Long,Integer> products;

    private boolean state;
    public CartEntity(Long id, HashMap<Long, Integer> products) {
        this.id = id;
        this.products = products;
        this.state=false;
    }

    public CartEntity(CartBo cartBo){
        this.id=cartBo.getId();
        this.products= cartBo.getCartProducts();
        this.state= cartBo.isState();
    }
    public CartBo toCartBo(){
        return new CartBo(this.getId(),this.getProducts());
    }
}
