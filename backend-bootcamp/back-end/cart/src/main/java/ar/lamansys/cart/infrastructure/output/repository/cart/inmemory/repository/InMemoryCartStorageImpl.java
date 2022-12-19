package ar.lamansys.cart.infrastructure.output.repository.cart.inmemory.repository;

import ar.lamansys.cart.application.ports.CartStorage;
import ar.lamansys.cart.domain.cart.CartBo;
import ar.lamansys.cart.infrastructure.output.repository.cart.inmemory.entity.CartEntity;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryCartStorageImpl implements CartStorage {
    Map<Long, CartEntity> dataCart =new HashMap<>();

    /*public InMemoryCartStorageImpl(){
        HashMap<Long,Integer> p1= new HashMap<>();
        p1.put(1L,3);
        CartEntity c1= new CartEntity(1L, p1);
        CartEntity c2= new CartEntity(2L,null);
        this.dataCart.put(1L,c1);
        this.dataCart.put(2L, c2);

    }*/
    @Override
    public Optional<CartBo> findById( Long id ){
        if( dataCart.containsKey(id) )
            return Optional.of( dataCart.get(id).toCartBo() );
        return Optional.empty();
    }

    @Override
    public void save (CartBo cart){
        dataCart.put( cart.getId(), new CartEntity( cart) );
    }


}
