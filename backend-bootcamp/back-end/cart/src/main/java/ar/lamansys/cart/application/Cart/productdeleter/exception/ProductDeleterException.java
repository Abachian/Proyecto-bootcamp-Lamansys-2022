package ar.lamansys.cart.application.Cart.productdeleter.exception;

import lombok.Getter;

public class ProductDeleterException extends RuntimeException {
    @Getter
    CodeProductDeleterException code;

    public ProductDeleterException(CodeProductDeleterException code, String message) {
        super(message);
        this.code = code;
    }
}
