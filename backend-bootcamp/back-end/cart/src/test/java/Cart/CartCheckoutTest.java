package Cart;

import ar.lamansys.cart.application.Cart.cartcheckout.CartCheckout;
import ar.lamansys.cart.application.Cart.cartcheckout.exception.CartCheckoutException;
import ar.lamansys.cart.application.Cart.cartcheckout.exception.CodeCartCheckoutException;
import ar.lamansys.cart.application.Cart.cartcreator.CartCreator;
import ar.lamansys.cart.application.Cart.productadder.exception.CodeProductAdderException;
import ar.lamansys.cart.application.Cart.productadder.exception.ProductAdderException;
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
public class CartCheckoutTest {
    @Mock
    private CartStorage cartStorage;

    @Mock
    private ProductStockStorage productStockStorage;

    @InjectMocks
    CartCheckout cartCheckout;

    @Test
    void cartCheckout_run_correct() {
        var productCartStock1=  new ProductStockBo(2L,"keyboard","gaming keyboard",2,20.0);

        var productCartStock2=  new ProductStockBo(3L,"mouse","gaming mouse",2,10.0);

        var cartProductsTest= new HashMap();

        cartProductsTest.put(2L,1);
        cartProductsTest.put(3L,1);

        var cartTest= new CartBo(1L,cartProductsTest,false);

        when(productStockStorage.findById(2L)).thenReturn(Optional.
                of(productCartStock1));

        when(productStockStorage.findById(3L)).thenReturn(Optional.of(productCartStock2));

        when(cartStorage.findById(1L)).thenReturn(Optional.of(cartTest));

        //act
        var cart= cartCheckout.run(String.valueOf( cartTest.getId()) );
        //assert
        verify(cartStorage,times(2)).findById(1L);
        verify(productStockStorage,times(1)).findById(2L);
        verify(productStockStorage,times(1)).findById(3L);
        verify(productStockStorage,times(1)).discountQuantity(2L,1);
        verify(productStockStorage,times(1)).discountQuantity(3L,1);
        verify(cartStorage,times(1)).save(cartTest);

        assertThat(cart.isState()).isEqualTo(true);

    }
    @Test
    void cart_already_sold_exception(){

        var cartProductsTest= new HashMap();

        cartProductsTest.put(2L,1);

        var cartTest= new CartBo(1L,cartProductsTest,true);

        when(cartStorage.findById(1L)).thenReturn(Optional.of(cartTest));


        var thrown = catchThrowable(() -> cartCheckout.
                run(String.valueOf(cartTest.getId())) ) ;

        assertThat(thrown).isInstanceOf(CartCheckoutException.class).
                extracting("code").isEqualTo(CodeCartCheckoutException.CART_ALREADY_SOLD);
    }

    @Test
    void cart_id_not_found_exception(){

        var cartProductsTest= new HashMap();

        cartProductsTest.put(2L,1);

        var cartTest= new CartBo(1L,cartProductsTest,false);

        var thrown = catchThrowable(() -> cartCheckout.
                run(String.valueOf(cartTest.getId())) ) ;

        assertThat(thrown).isInstanceOf(CartCheckoutException.class).
                extracting("code").isEqualTo(CodeCartCheckoutException.CART_ID_NOT_FOUND);
    }

    @Test
    void product_quantity_exception(){

        var productCartStock1=  new ProductStockBo(2L,"keyboard","gaming keyboard",2,20.0);

        var cartProductsTest= new HashMap();

        cartProductsTest.put(2L,3);

        var cartTest= new CartBo(1L,cartProductsTest,false);

        when(productStockStorage.findById(2L)).thenReturn(Optional.
                of(productCartStock1));

        when(cartStorage.findById(1L)).thenReturn(Optional.of(cartTest));

        var thrown = catchThrowable(() -> cartCheckout.
                run(String.valueOf(cartTest.getId())) ) ;

        assertThat(thrown).isInstanceOf(CartCheckoutException.class).
                extracting("code").isEqualTo(CodeCartCheckoutException.PRODUCT_QUANTITY_HIGHER_THAN_STOCK);
    }
}
