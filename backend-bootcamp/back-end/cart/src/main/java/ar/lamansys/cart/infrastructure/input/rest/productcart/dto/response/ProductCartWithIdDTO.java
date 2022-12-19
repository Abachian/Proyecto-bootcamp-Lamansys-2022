package ar.lamansys.cart.infrastructure.input.rest.productcart.dto.response;
import ar.lamansys.cart.infrastructure.input.rest.productcart.dto.request.ProductCartDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProductCartWithIdDTO extends ProductCartDTO {
    private Long id;

    public ProductCartWithIdDTO(Long id, String name, double price, String description,  Integer quantity) {
        super(name, description, price, quantity, id);
        this.id = id;
    }
}
