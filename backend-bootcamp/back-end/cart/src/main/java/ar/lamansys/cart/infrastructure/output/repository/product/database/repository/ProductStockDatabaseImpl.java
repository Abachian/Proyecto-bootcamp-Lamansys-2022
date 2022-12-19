package ar.lamansys.cart.infrastructure.output.repository.product.database.repository;

import ar.lamansys.cart.application.ports.ProductStockStorage;
import ar.lamansys.cart.domain.product.ProductStockBo;
import ar.lamansys.cart.infrastructure.input.rest.productcart.adapter.ProductAdapter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Primary
@AllArgsConstructor
public class ProductStockDatabaseImpl implements ProductStockStorage {

    ProductStockJpaRepository productStockJpaRepository;
    ProductAdapter productAdapter;

    @Override
    public Optional<ProductStockBo> findById(Long id ){
        return productStockJpaRepository.findById(id).
                map(productStockJpaEntity -> productStockJpaEntity.toProductStockBo());
    }
    @Override
    public double getPrice(Long id ){
        return productStockJpaRepository.findById(id).get().getPrice();
    }
    @Override
    public Optional<ProductStockBo> findByName(String name ){
        return productStockJpaRepository.findByName(name)
                .map(productStockJpaEntity -> productStockJpaEntity.toProductStockBo());

    }
    @Override
    public List<ProductStockBo> findAll(){
        return productStockJpaRepository.findAll().stream().map( productStockJpaEntity ->productStockJpaEntity.toProductStockBo() )
                .collect(Collectors.toList());
    }

    @Override
    public void discountQuantity(Long id, Integer quantity) {
        Integer actualQuantity= productStockJpaRepository.getById(id).getQuantity();
        productStockJpaRepository.getById(id).setQuantity(actualQuantity- quantity);

    }
    @Override
    public void save(ProductStockBo productStockBo){

        productStockJpaRepository.save(productAdapter.toProductStockJpaEntity(productStockBo));
    }
}
