package ar.lamansys.cart.infrastructure.output.repository.product.database.entity;


import ar.lamansys.cart.domain.product.ProductStockBo;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name= "productsStock")
@NoArgsConstructor
public class ProductStockJpaEntity {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private double price;
    @Column(name= "quantity")
    private Integer quantity;



    public ProductStockJpaEntity(Long id, String name, String description, double price, Integer quantity) {
        this.id=id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public ProductStockBo toProductStockBo(){
        return new ProductStockBo(this.getId(),this.getName(),this.getDescription(), this.getQuantity(), this.getPrice());
    }
}
