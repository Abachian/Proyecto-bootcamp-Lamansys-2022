package ar.lamansys.cart.infrastructure.output.repository.cart.database.repository;

import ar.lamansys.cart.application.ports.CartStorage;
import ar.lamansys.cart.domain.cart.CartBo;
import ar.lamansys.cart.infrastructure.input.rest.cart.adapter.CartAdapter;
import ar.lamansys.cart.infrastructure.output.repository.cart.database.entity.CartJpaEntity;
import ar.lamansys.cart.infrastructure.output.repository.cart.database.mapper.CartJpaMapper;
import ar.lamansys.cart.infrastructure.output.repository.product.database.entity.ProductCartJpaEntity;
import ar.lamansys.cart.infrastructure.output.repository.product.database.entity.ProductCartKey;
import ar.lamansys.cart.infrastructure.output.repository.product.database.entity.ProductStockJpaEntity;
import ar.lamansys.cart.infrastructure.output.repository.product.database.repository.ProductCartJpaRepository;
import ar.lamansys.cart.infrastructure.output.repository.product.database.repository.ProductStockJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@AllArgsConstructor
@Repository
@Primary
public class CartDatabaseImpl implements CartStorage {
    private CartJpaRepository cartJpaRepository;
    private ProductCartJpaRepository productCartJpaRepository;
    private ProductStockJpaRepository productStockJpaRepository;


    @Override
    public Optional<CartBo> findById(Long id) {
        Optional<CartJpaEntity> cart= cartJpaRepository.findById(id);
        return cart.map(CartJpaMapper::toCartBo);
    }

    @Override
    public void save(CartBo cartBo) {

        cartJpaRepository.save(new CartJpaEntity(cartBo.getId(), cartBo.isState()));

        CartJpaEntity cart= cartJpaRepository.findByIdJpa(cartBo.getId()).get();

        for (Long idProduct: cartBo.getCartProducts().keySet()){
            ProductCartKey productInCartKey= new ProductCartKey(cart.getId(),idProduct);

            ProductStockJpaEntity product= productStockJpaRepository.getById(idProduct);

            productCartJpaRepository.save(new ProductCartJpaEntity(productInCartKey, cart, product,
                    cartBo.getCartProducts().get(idProduct)));
        }

    }

}
