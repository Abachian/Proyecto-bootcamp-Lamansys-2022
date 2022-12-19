package ar.lamansys.cart.infrastructure.input.rest.exception;

import ar.lamansys.cart.application.Cart.cartcreator.exception.CartCreatorException;
import ar.lamansys.cart.domain.exception.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "ar.lamansys.cart.infrastructure.input.rest")
public class HandlerException {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<DTOError> getError(DomainException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new DTOError(e.getMessage(), e.getCode().toString()));
    }

    @ExceptionHandler(CartCreatorException.class)
    public ResponseEntity<DTOError> getError(CartCreatorException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new DTOError(e.getMessage(), e.getCode().toString()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DTOError> getError(Exception e) {
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new DTOError(e.getMessage(), null));
    }

}
