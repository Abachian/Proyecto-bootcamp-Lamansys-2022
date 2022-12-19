package ar.lamansys.cart.infrastructure.input.rest.cart.adapter;

import ar.lamansys.cart.domain.cart.CartBo;
import ar.lamansys.cart.infrastructure.input.rest.cart.dto.request.CartDTO;
import ar.lamansys.cart.infrastructure.input.rest.cart.dto.response.CartWithIdDTO;
import ar.lamansys.cart.infrastructure.input.rest.cart.dto.response.CartWithIdDTO.CartWithIdDTOBuilder;
import ar.lamansys.cart.infrastructure.output.repository.cart.inmemory.entity.CartEntity;
import java.util.HashMap;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-29T21:44:55-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class CartAdapterImpl implements CartAdapter {

    @Override
    public CartBo toCartBo(CartWithIdDTO cartWithIdDTO) {
        if ( cartWithIdDTO == null ) {
            return null;
        }

        CartBo cartBo = new CartBo();

        cartBo.setId( cartWithIdDTO.getId() );
        HashMap<Long, Integer> hashMap = cartWithIdDTO.getCartProducts();
        if ( hashMap != null ) {
            cartBo.setCartProducts( new HashMap<Long, Integer>( hashMap ) );
        }

        return cartBo;
    }

    @Override
    public CartWithIdDTO toCartWithIdDTO(CartBo cartBo) {
        if ( cartBo == null ) {
            return null;
        }

        CartWithIdDTOBuilder cartWithIdDTO = CartWithIdDTO.builder();

        cartWithIdDTO.id( cartBo.getId() );
        HashMap<Long, Integer> hashMap = cartBo.getCartProducts();
        if ( hashMap != null ) {
            cartWithIdDTO.cartProducts( new HashMap<Long, Integer>( hashMap ) );
        }

        return cartWithIdDTO.build();
    }

    @Override
    public CartBo toCartBo(CartEntity cartEntity) {
        if ( cartEntity == null ) {
            return null;
        }

        CartBo cartBo = new CartBo();

        cartBo.setId( cartEntity.getId() );
        cartBo.setState( cartEntity.isState() );

        return cartBo;
    }

    @Override
    public CartBo toCartBo(CartDTO cartDTO) {
        if ( cartDTO == null ) {
            return null;
        }

        CartBo cartBo = new CartBo();

        HashMap<Long, Integer> hashMap = cartDTO.getCartProducts();
        if ( hashMap != null ) {
            cartBo.setCartProducts( new HashMap<Long, Integer>( hashMap ) );
        }

        return cartBo;
    }
}
