package ar.lamansys.cart.domain.exception;

import lombok.Getter;

public class DomainException extends RuntimeException {
    @Getter
    private String code;

    public DomainException(final String code, final String message) {
        super(message);
        this.code = code;
    }
}
