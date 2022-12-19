package ar.lamansys.cart.infrastructure.input.rest.productcart.adapter;
import ar.lamansys.cart.application.ports.ProductStockStorage;
import ar.lamansys.cart.domain.product.ProductCartBo;
import ar.lamansys.cart.domain.product.ProductStockBo;
import ar.lamansys.cart.infrastructure.input.rest.productcart.dto.request.ProductCartDTO;
import ar.lamansys.cart.infrastructure.output.repository.product.database.entity.ProductStockJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel="spring")
public interface ProductAdapter {


    @Mapping(source = "id", target = "id")
    ProductCartBo toProductCartBo(ProductCartDTO productCartDTO);

    @Mapping(source = "id", target = "id")
    ProductStockBo toProductStockBo(ProductCartDTO productCartDTO);


    @Mapping(source = "id", target = "id")
    ProductStockJpaEntity toProductStockJpaEntity(ProductStockBo productStockBo);

}
