package ar.lamansys.cart.infrastructure.output.repository.product.inmemory.repository;

import ar.lamansys.cart.application.ports.ProductStockStorage;
import ar.lamansys.cart.domain.product.ProductStockBo;
import ar.lamansys.cart.infrastructure.output.repository.product.inmemory.entity.ProductStockEntity;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemmoryProductStockImpl implements ProductStockStorage {
    Map<Long, ProductStockEntity> dataProduct =new HashMap<>();

    @Override
    public Optional<ProductStockBo> findById(Long id ){
        if( dataProduct.containsKey(id) )
            return Optional.of( dataProduct.get(id).toProductStockBo() );
        return Optional.empty();
    }
    @Override
    public double getPrice(Long id ){
        if( dataProduct.containsKey(id) )
            return dataProduct.get(id).getPrice();
        return 0.0;
    }
    @Override
    public Optional<ProductStockBo> findByName(String name ){
        if( dataProduct.containsValue(name) )
            return Optional.of( dataProduct.get(name).toProductStockBo() );
        return Optional.empty();
    }
    @Override
    public List<ProductStockBo> findAll(){
        return dataProduct.values().stream().map( productStockEntity -> productStockEntity.toProductStockBo() )
                .collect(Collectors.toList());
    }

    @Override
    public void discountQuantity(Long id, Integer quantity) {
        Integer actualQuantity= dataProduct.get(id).getQuantity();
        dataProduct.get(id).setQuantity(actualQuantity- quantity);
    }
    @Override
    public void save(ProductStockBo productStockBo){
        dataProduct.put(productStockBo.getId(), new ProductStockEntity(productStockBo) );
    }
}
