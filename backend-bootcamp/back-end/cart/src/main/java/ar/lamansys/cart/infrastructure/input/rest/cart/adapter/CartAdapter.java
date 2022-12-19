package ar.lamansys.cart.infrastructure.input.rest.cart.adapter;

import ar.lamansys.cart.domain.cart.CartBo;

import ar.lamansys.cart.infrastructure.input.rest.cart.dto.request.CartDTO;
import ar.lamansys.cart.infrastructure.input.rest.cart.dto.response.CartWithIdDTO;
import ar.lamansys.cart.infrastructure.output.repository.cart.inmemory.entity.CartEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface CartAdapter {
    @Mapping(source = "id", target = "id")
    CartBo toCartBo(CartWithIdDTO cartWithIdDTO);

    @Mapping(source = "id", target = "id")
    CartWithIdDTO toCartWithIdDTO(CartBo cartBo);

    @Mapping(source = "id", target = "id")
    CartBo toCartBo(CartEntity cartEntity);

    @Mapping(source = "cartProducts", target = "cartProducts")
    CartBo toCartBo(CartDTO cartDTO);
}
