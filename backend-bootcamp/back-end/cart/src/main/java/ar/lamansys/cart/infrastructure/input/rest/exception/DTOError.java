package ar.lamansys.cart.infrastructure.input.rest.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOError {
    String message;
    String code;
}
