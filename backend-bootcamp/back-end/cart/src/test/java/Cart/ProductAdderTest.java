package Cart;

import ar.lamansys.cart.application.Cart.cartcreator.CartCreator;
import ar.lamansys.cart.application.Cart.cartcreator.exception.CartCreatorException;
import ar.lamansys.cart.application.Cart.cartcreator.exception.CodeCartCreatorException;
import ar.lamansys.cart.application.Cart.productadder.ProductAdder;
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
public class ProductAdderTest {
    @Mock
    private CartStorage cartStorage;

    @Mock
    private ProductStockStorage productStockStorage;

    @InjectMocks
    ProductAdder productAdder;

    @Test
    void productAdder_run_correct() {
        var productCart = new ProductCartBo("keyboard", "gaming keyboard", 1, 20.0, 2L);

        var productCartStock = new ProductStockBo(2L, "keyboard", "gaming keyboard", 5, 20.0);

        var cartProductsTest = new HashMap();

        cartProductsTest.put(2L, 1);

        var cartTest = new CartBo(1L, cartProductsTest, false);

        when(productStockStorage.findById(2L)).thenReturn(Optional.
                of(productCartStock));

        when(cartStorage.findById(1L)).thenReturn(Optional.of(cartTest));

        var id = productAdder.
                run(String.valueOf(cartTest.getId()), productCart.getId(), productCart.getQuantity()).get().getId();

        verify(cartStorage, times(2)).findById(cartTest.getId());
        verify(productStockStorage, times(3)).findById(productCart.getId());
        verify(cartStorage, times(1)).save(cartTest);

        assertThat(cartStorage.findById(cartTest.getId()).get().getCartProducts()
                .get(productCart.getId()).equals(2));
    }

    @Test
    void product_id_not_found_exception() {
        var productCart = new ProductCartBo("keyboard", "gaming keyboard", 1, 20.0, 2L);
        var cartProductsTest = new HashMap();
        cartProductsTest.put(2L, 1);
        var cartTest = new CartBo(1L, cartProductsTest, false);
        when(cartStorage.findById(1L)).thenReturn(Optional.of(cartTest));

        var thrown = catchThrowable(() -> productAdder.
                run(String.valueOf(cartTest.getId()), productCart.getId(), productCart.getQuantity()));

        assertThat(thrown).isInstanceOf(ProductAdderException.class).
                extracting("code").isEqualTo(CodeProductAdderException.PRODUCT_ID_NOT_FOUND);

    }

    @Test
    void product_quantity_higher_exception() {
        var productCart = new ProductCartBo("keyboard", "gaming keyboard", 2, 20.0, 2L);

        var productCartStock = new ProductStockBo(2L, "keyboard", "gaming keyboard", 1, 20.0);

        var cartProductsTest = new HashMap();

        cartProductsTest.put(2L, 1);

        var cartTest = new CartBo(1L, cartProductsTest, false);

        when(productStockStorage.findById(2L)).thenReturn(Optional.
                of(productCartStock));
        when(cartStorage.findById(1L)).thenReturn(Optional.of(cartTest));

        var thrown = catchThrowable(() -> productAdder.
                run(String.valueOf(cartTest.getId()), productCart.getId(), productCart.getQuantity()));

        assertThat(thrown).isInstanceOf(ProductAdderException.class).
                extracting("code").isEqualTo(CodeProductAdderException.PRODUCT_QUANTITY_HIGHER_THAN_STOCK);
    }

    @Test
    void product_quantity_null_exception() {
        var productCart = new ProductCartBo("keyboard", "gaming keyboard", 0, 20.0, 2L);

        var productCartStock = new ProductStockBo(2L, "keyboard", "gaming keyboard", 1, 20.0);

        var cartProductsTest = new HashMap();

        cartProductsTest.put(2L, 0);

        var cartTest = new CartBo(1L, cartProductsTest, false);

        when(productStockStorage.findById(2L)).thenReturn(Optional.
                of(productCartStock));
        when(cartStorage.findById(1L)).thenReturn(Optional.of(cartTest));

        var thrown = catchThrowable(() -> productAdder.
                run(String.valueOf(cartTest.getId()), productCart.getId(), productCart.getQuantity()));

        assertThat(thrown).isInstanceOf(ProductAdderException.class).
                extracting("code").isEqualTo(CodeProductAdderException.PRODUCT_QUANTITY_NULL);

    }

    @Test
    void cart_id_not_found_exception(){

    var productCart= new ProductCartBo("keyboard", "gaming keyboard", 1, 20.0, 2L);

    var cartProductsTest= new HashMap();

        cartProductsTest.put(2L,1);

    var cartTest= new CartBo(1L,cartProductsTest,false);



    var thrown = catchThrowable(() -> productAdder.
            run(String.valueOf(cartTest.getId()), productCart.getId(), productCart.getQuantity()));

    assertThat(thrown).isInstanceOf(ProductAdderException.class).
            extracting("code").isEqualTo(CodeProductAdderException.CART_ID_NOT_FOUND);
    }
    @Test
    void cart_sold_exception(){
        var productCart= new ProductCartBo("keyboard", "gaming keyboard", 1, 20.0, 2L);

        var productCartStock = new ProductStockBo(2L, "keyboard", "gaming keyboard", 1, 20.0);

        var cartProductsTest= new HashMap();

        cartProductsTest.put(2L,1);

        var cartTest= new CartBo(1L,cartProductsTest,true);

        when(productStockStorage.findById(2L)).thenReturn(Optional.
                of(productCartStock));
        when(cartStorage.findById(1L)).thenReturn(Optional.of(cartTest));


        var thrown = catchThrowable(() -> productAdder.
                run(String.valueOf(cartTest.getId()), productCart.getId(), productCart.getQuantity()));

        assertThat(thrown).isInstanceOf(ProductAdderException.class).
                extracting("code").isEqualTo(CodeProductAdderException.CART_SOLD);
    }


}
