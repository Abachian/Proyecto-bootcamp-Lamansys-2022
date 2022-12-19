package ar.lamansys.cart.domain.product;


import ar.lamansys.cart.domain.exception.DomainException;
import lombok.*;
import ar.lamansys.cart.domain.AutoincrementId;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
@Setter
@Getter
@EqualsAndHashCode
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductStockBo {
    private static final AtomicInteger count = new AtomicInteger(1);
    private Long id;
    private String name;
    private String description;
    private Integer quantity;
    private double price;


    public ProductStockBo(String name, String description, double price, Integer quantity) {
        validatePrice(price);
        validateName(name);
        validateQuantity(quantity);
        this.id = AutoincrementId.incrementAndGetId();
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public ProductStockBo(Long id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public ProductStockBo(Long id, String name, String description, double price, Integer quantity) {
        validateId(id);
        validatePrice(price);
        validateName(name);
        validateQuantity(quantity);
        this.id = AutoincrementId.incrementAndGetId();
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public ProductCartBo toProductCartBo(){
        return new ProductCartBo(this.getName(),this.getDescription(),this.getQuantity() ,this.getPrice(), this.getId());
    }
    public void validateId(Long id) {
        if (id == null) {
            throw new DomainException("product_id_not_null", "The product id cannot be null.");
        }
    }

    public void validateName(String name) {
        if (name == null) {
            throw new DomainException("product_name_not_null", "The product name cannot be null.");
        }
    }
    public void validatePrice(double price) {
        if (price == 0.0) {
            throw new DomainException("product_price_0", "The product price cannot be 0.");
        }
    }
    public void validateQuantity(Integer quantity) {
        if (quantity == 0) {
            throw new DomainException("product_quantity_0", "The product quantity cannot be 0.");
        }
    }



}
