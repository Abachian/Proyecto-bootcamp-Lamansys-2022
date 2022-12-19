package ar.lamansys.cart.application.Cart.cartcreator.exception;

import lombok.Getter;

public class CartCreatorException extends RuntimeException{
    @Getter
    CodeCartCreatorException code;

    public CartCreatorException(CodeCartCreatorException code, String message) {
        super(message);
        this.code = code;
    }
}
