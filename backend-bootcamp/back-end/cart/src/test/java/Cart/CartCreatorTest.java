package Cart;

import ar.lamansys.cart.application.Cart.cartcreator.CartCreator;
import ar.lamansys.cart.application.Cart.cartcreator.exception.CartCreatorException;
import ar.lamansys.cart.application.Cart.cartcreator.exception.CodeCartCreatorException;
import ar.lamansys.cart.application.ports.CartStorage;
import ar.lamansys.cart.application.ports.ProductStockStorage;
import ar.lamansys.cart.domain.cart.CartBo;
import ar.lamansys.cart.domain.product.ProductCartBo;
import ar.lamansys.cart.domain.product.ProductStockBo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
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

    @InjectMocks
    CartCreator cartCreator;


    @Test
    void cartCreator_run_correct() {

        var productCart= new ProductCartBo("keyboard", "gaming keyboard", 1, 20.0, 2L);

        var productCartStock=  new ProductStockBo(2L,"keyboard","gaming keyboard",1,20.0);

        var cartProductsTest= new HashMap();

        cartProductsTest.put(2L,1);

        var cartTest= new CartBo(1L,cartProductsTest,false);

        when(productStockStorage.findById(2L)).thenReturn(Optional.
                of(productCartStock));


        //act
        var id=cartCreator.run(cartTest, productCart);
        //assert
        verify(cartStorage,times(1)).findById(1L);
        verify(productStockStorage,times(2)).findById(2L);
        verify(cartStorage,times(1)).save(cartTest);


        assertThat(id.equals(1L));

    }

    @Test
    void product_id_not_found_exception(){
        var productCart= new ProductCartBo("keyboard", "gaming keyboard", 1, 20.0, 2L);
        var cartProductsTest= new HashMap();

        cartProductsTest.put(2L,1);

        var cartTest= new CartBo(1L,cartProductsTest,false);


        var thrown=catchThrowable(()-> cartCreator.run(cartTest, productCart));

        assertThat(thrown).isInstanceOf(CartCreatorException.class).extracting("code").isEqualTo(CodeCartCreatorException.PRODUCT_ID_NOT_FOUND);

    }

    @Test
    void cart_empty_exception(){

        var productCart= new ProductCartBo("keyboard", "gaming keyboard", 0, 20.0, 2L);

        var productCartStock=  new ProductStockBo(2L,"keyboard","gaming keyboard",1,20.0);

        var cartProductsTest= new HashMap();

        cartProductsTest.put(2L,1);

        var cartTest= new CartBo(1L,cartProductsTest,false);

        when(productStockStorage.findById(2L)).thenReturn(Optional.
                of(productCartStock));


        var thrown=catchThrowable(()-> cartCreator.run(cartTest, productCart));

        assertThat(thrown).isInstanceOf(CartCreatorException.class).extracting("code").isEqualTo(CodeCartCreatorException.CART_CANNOT_BE_EMPTY);
    }

    @Test
    void cart_already_created_exception(){
        var productCart= new ProductCartBo("keyboard", "gaming keyboard", 1, 20.0, 2L);

        var cartProductsTest= new HashMap();

        cartProductsTest.put(2L,1);

        var cartTest= new CartBo(1L,cartProductsTest,false);

        when(cartStorage.findById( 1L) ).thenReturn(Optional.of(cartTest));


        var thrown=catchThrowable(()-> cartCreator.run(cartTest, productCart));

        assertThat(thrown).isInstanceOf(CartCreatorException.class).extracting("code").
                isEqualTo(CodeCartCreatorException.CART_ID_ALREADY_CREATED);
    }

    @Test
    void product_quantity_higher_exception(){
        var productCart= new ProductCartBo("keyboard", "gaming keyboard", 2, 20.0, 2L);

        var productCartStock=  new ProductStockBo(2L,"keyboard","gaming keyboard",1,20.0);

        var cartProductsTest= new HashMap();

        cartProductsTest.put(2L,1);

        var cartTest= new CartBo(1L,cartProductsTest,false);

        when(productStockStorage.findById(2L)).thenReturn(Optional.
                of(productCartStock));


        var thrown=catchThrowable(()-> cartCreator.run(cartTest, productCart));

        assertThat(thrown).isInstanceOf(CartCreatorException.class).extracting("code").isEqualTo(CodeCartCreatorException.PRODUCT_QUANTITY_HIGHER_THAN_STOCK);
    }




}