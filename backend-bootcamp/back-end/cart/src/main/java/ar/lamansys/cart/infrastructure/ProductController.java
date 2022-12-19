package ar.lamansys.cart.infrastructure;

import ar.lamansys.cart.application.product.ProductAddInStock;
import ar.lamansys.cart.infrastructure.input.rest.productcart.adapter.ProductAdapter;
import ar.lamansys.cart.infrastructure.input.rest.productcart.dto.request.ProductCartDTO;
import ar.lamansys.cart.infrastructure.input.rest.productcart.dto.response.ProductCartIdDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductAddInStock product;
    @Autowired
    ProductAdapter productAdapter;
    @PostMapping
    public ProductCartIdDTO addProductInStock(@RequestBody ProductCartDTO productToSave){
        final Long id = product.run( productAdapter.toProductStockBo(productToSave) );
        return new ProductCartIdDTO(id);
    }
}
