package ar.lamansys.cart.application.Cart.productadder.exception;

import lombok.Getter;

public class ProductAdderException extends RuntimeException{
    @Getter
    CodeProductAdderException code;

    public ProductAdderException(CodeProductAdderException code, String message) {
        super(message);
        this.code = code;
    }
}
