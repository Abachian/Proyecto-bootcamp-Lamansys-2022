package Cart;

import ar.lamansys.cart.application.Cart.productadder.exception.CodeProductAdderException;
import ar.lamansys.cart.application.Cart.productadder.exception.ProductAdderException;
import ar.lamansys.cart.application.Cart.productdeleter.ProductDeleter;
import ar.lamansys.cart.application.Cart.totalpricegetter.TotalPriceGetter;
import ar.lamansys.cart.application.Cart.totalpricegetter.exception.CodeTotalPriceGetteException;
import ar.lamansys.cart.application.Cart.totalpricegetter.exception.TotalPriceGetteException;
import ar.lamansys.cart.application.ports.CartStorage;
import ar.lamansys.cart.application.ports.ProductStockStorage;
import ar.lamansys.cart.domain.cart.CartBo;
import ar.lamansys.cart.domain.product.ProductCartBo;
import ar.lamansys.cart.domain.product.ProductStockBo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TotalPriceGetterTest {
    @Mock
    private CartStorage cartStorage;

    @Mock
    private ProductStockStorage productStockStorage;

    @InjectMocks
    TotalPriceGetter totalPriceGetter;

    @Test
    void totalPriceGetter_run_correct() {
        var productCart = new ProductCartBo("keyboard", "gaming keyboard", 1, 20.0, 2L);

        var cartProductsTest = new HashMap();

        cartProductsTest.put(2L, 2);

        var cartTest = new CartBo(1L, cartProductsTest, false);


        when(cartStorage.findById(1L)).thenReturn(Optional.of(cartTest));

        when(productStockStorage.getPrice(2L)).thenReturn(20.0);

        var price = totalPriceGetter.
                run(String.valueOf( cartTest.getId() ));

        verify(cartStorage, times(2)).findById(cartTest.getId());
        verify(productStockStorage, times(1)).getPrice(productCart.getId());

        assertThat(price).isEqualTo(40.0);
    }
    @Test
    void cart_id_not_found_exception(){

        var cartProductsTest= new HashMap();

        cartProductsTest.put(2L,1);

        var cartTest= new CartBo(1L,cartProductsTest,false);


        var thrown =catchThrowable(()-> totalPriceGetter.
                run(String.valueOf( cartTest.getId() ) ) );

        assertThat(thrown).isInstanceOf(TotalPriceGetteException.class).
                extracting("code").isEqualTo(CodeTotalPriceGetteException.CART_ID_NOT_FOUND);
    }
}
