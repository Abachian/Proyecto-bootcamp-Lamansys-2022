package ar.lamansys.cart.application.Cart.totalpricegetter.exception;

import ar.lamansys.cart.application.Cart.productdeleter.exception.CodeProductDeleterException;
import lombok.Getter;

public class TotalPriceGetteException extends RuntimeException{
    @Getter
    CodeTotalPriceGetteException code;

    public TotalPriceGetteException(CodeTotalPriceGetteException code, String message) {
        super(message);
        this.code = code;
    }
}
