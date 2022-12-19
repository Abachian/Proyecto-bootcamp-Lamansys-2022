package ar.lamansys.cart.application.Cart.cartcheckout.exception;

import ar.lamansys.cart.application.Cart.cartcreator.exception.CodeCartCreatorException;
import lombok.Getter;

public class CartCheckoutException extends RuntimeException{
    @Getter
    CodeCartCheckoutException code;

    public CartCheckoutException(CodeCartCheckoutException code, String message) {
        super(message);
        this.code = code;
    }
}
