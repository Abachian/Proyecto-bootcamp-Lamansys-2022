package ar.lamansys.cart.infrastructure.output.repository.product.inmemory.entity;

import ar.lamansys.cart.domain.product.ProductStockBo;
import lombok.Data;

@Data
public class ProductStockEntity {
    private Long id;
    private String name;
    private String description;
    private double price;
    private Integer quantity;

    public ProductStockEntity(Long id, String name, String description, double price, Integer quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public ProductStockEntity(ProductStockBo productStockBO){
        this.id= productStockBO.getId();
        this.name= productStockBO.getName();
        this.description= productStockBO.getDescription();
        this.price= productStockBO.getPrice();
    }

    public ProductStockBo toProductStockBo(){
        return new ProductStockBo(this.getId(),this.getName(),this.getDescription(), this.getQuantity(), this.getPrice());
    }
}
