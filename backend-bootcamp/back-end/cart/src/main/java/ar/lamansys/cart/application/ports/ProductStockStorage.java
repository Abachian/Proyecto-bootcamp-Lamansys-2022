package ar.lamansys.cart.application.ports;


import ar.lamansys.cart.domain.product.ProductStockBo;

import java.util.List;
import java.util.Optional;

public interface ProductStockStorage {
    Optional<ProductStockBo> findById(Long id);
    Optional<ProductStockBo> findByName(String name);
    List<ProductStockBo> findAll();
    double getPrice(Long id);
    void discountQuantity(Long id, Integer quantity);
    void save(ProductStockBo productStockBo);

}
