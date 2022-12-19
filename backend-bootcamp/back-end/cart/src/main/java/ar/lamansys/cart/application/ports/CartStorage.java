package ar.lamansys.cart.application.ports;



import ar.lamansys.cart.domain.cart.CartBo;

import java.util.Optional;

public interface CartStorage {
    Optional<CartBo> findById(Long id);
    void save(CartBo cartBo);
}
