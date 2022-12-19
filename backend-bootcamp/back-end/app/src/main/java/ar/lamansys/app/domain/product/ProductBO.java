package ar.lamansys.app.domain.product;

import ar.lamansys.app.domain.AutoincrementId;
import com.sun.istack.NotNull;
import lombok.*;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@EqualsAndHashCode
public class ProductBO {
    private static final AtomicInteger count = new AtomicInteger(0);
    Long id;
    @Setter
    @NotNull
    String name;
    @Setter
    String description;
    @Setter
    double price;

    public ProductBO(String name, String description, double price) {

        validatePrice(price);

        this.id = AutoincrementId.incrementAndGetId();
        this.name = name;
        this.description = description;
        this.price = price;
    }

    private void validatePrice(double price) {

    }
}
