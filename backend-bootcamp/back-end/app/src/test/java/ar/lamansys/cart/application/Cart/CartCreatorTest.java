package ar.lamansys.cart.application.Cart;

import ar.lamansys.cart.application.Cart.cartcreator.CartCreator;
import ar.lamansys.cart.application.ports.CartStorage;
import ar.lamansys.cart.application.ports.ProductStockStorage;
import ar.lamansys.cart.domain.cart.CartBo;
import ar.lamansys.cart.domain.product.ProductCartBo;
import ar.lamansys.cart.domain.product.ProductStockBo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartCreatorTest {
    @Mock
    private CartStorage cartStorage;

    @Mock
    private ProductStockStorage productStockStorage;

    private CartCreator cartCreator= Mockito.mock(CartCreator.class);

    @BeforeEach
    void setUp() {
    }

    @Test
    void withOneProduct_run_completed() {
        var cartProductsTest= new HashMap();

        cartProductsTest.put(2L,1);

        var cartTest= new CartBo(1L,cartProductsTest,false);

        var productCart= new ProductCartBo("keyboard", "gaming keyboard", 1, 20.0, 2L);

        Mockito.when(productStockStorage.findById(2L)).thenReturn(Optional.
                of(new ProductStockBo(2L,"keyboard","gaming keyboard",1,20.0)));

        Mockito.when(cartStorage.findById( 1L) ).thenReturn(Optional.of( new CartBo (1L, cartProductsTest,false )));

        //act
        var thrown= catchThrowable(
                ()-> cartCreator.run(cartTest,productCart));
        //assert
        Mockito.verify(cartStorage,times(1)).findById(1L);
        Mockito.verify(productStockStorage,times(2)).findById(2L);
        Mockito.verify(cartStorage,times(1)).save(cartTest);
        Mockito.verify(cartCreator,times(1)).run(cartTest,productCart);
        assertThat(id.equals(1L));
    }
}