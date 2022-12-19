package ar.lamansys.cart.infrastructure.input.rest.productcart.adapter;

import ar.lamansys.cart.domain.product.ProductCartBo;
import ar.lamansys.cart.domain.product.ProductStockBo;
import ar.lamansys.cart.domain.product.ProductStockBo.ProductStockBoBuilder;
import ar.lamansys.cart.infrastructure.input.rest.productcart.dto.request.ProductCartDTO;
import ar.lamansys.cart.infrastructure.output.repository.product.database.entity.ProductStockJpaEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-29T22:26:59-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class ProductAdapterImpl implements ProductAdapter {

    @Override
    public ProductCartBo toProductCartBo(ProductCartDTO productCartDTO) {
        if ( productCartDTO == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String description = null;
        Integer quantity = null;

        id = productCartDTO.getId();
        name = productCartDTO.getName();
        description = productCartDTO.getDescription();
        quantity = productCartDTO.getQuantity();

        double price = 0.0d;

        ProductCartBo productCartBo = new ProductCartBo( name, description, quantity, price, id );

        productCartBo.setPrice( productCartDTO.getPrice() );

        return productCartBo;
    }

    @Override
    public ProductStockBo toProductStockBo(ProductCartDTO productCartDTO) {
        if ( productCartDTO == null ) {
            return null;
        }

        ProductStockBoBuilder productStockBo = ProductStockBo.builder();

        productStockBo.id( productCartDTO.getId() );
        productStockBo.name( productCartDTO.getName() );
        productStockBo.description( productCartDTO.getDescription() );
        productStockBo.quantity( productCartDTO.getQuantity() );
        productStockBo.price( productCartDTO.getPrice() );

        return productStockBo.build();
    }

    @Override
    public ProductStockJpaEntity toProductStockJpaEntity(ProductStockBo productStockBo) {
        if ( productStockBo == null ) {
            return null;
        }

        ProductStockJpaEntity productStockJpaEntity = new ProductStockJpaEntity();

        productStockJpaEntity.setId( productStockBo.getId() );
        productStockJpaEntity.setName( productStockBo.getName() );
        productStockJpaEntity.setDescription( productStockBo.getDescription() );
        productStockJpaEntity.setPrice( productStockBo.getPrice() );
        productStockJpaEntity.setQuantity( productStockBo.getQuantity() );

        return productStockJpaEntity;
    }
}
