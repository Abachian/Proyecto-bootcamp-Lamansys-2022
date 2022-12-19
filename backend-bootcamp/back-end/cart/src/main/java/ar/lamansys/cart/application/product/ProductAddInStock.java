package ar.lamansys.cart.application.product;

import ar.lamansys.cart.application.ports.ProductStockStorage;
import ar.lamansys.cart.domain.product.ProductStockBo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProductAddInStock {
    @Autowired
    ProductStockStorage productStockStorage;

    public Long run(ProductStockBo product){
        productStockStorage.save(product);
        return product.getId();
    }
}
