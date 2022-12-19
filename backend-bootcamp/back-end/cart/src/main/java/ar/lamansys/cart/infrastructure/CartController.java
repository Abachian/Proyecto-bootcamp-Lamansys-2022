package ar.lamansys.cart.infrastructure;
import ar.lamansys.cart.application.Cart.cartcheckout.CartCheckout;
import ar.lamansys.cart.application.Cart.productadder.ProductAdder;
import ar.lamansys.cart.application.Cart.cartcreator.CartCreator;
import ar.lamansys.cart.application.Cart.cartgetter.CartGetter;
import ar.lamansys.cart.application.Cart.productdeleter.ProductDeleter;
import ar.lamansys.cart.application.Cart.totalpricegetter.TotalPriceGetter;
import ar.lamansys.cart.domain.cart.CartBo;
import ar.lamansys.cart.domain.product.ProductCartBo;
import ar.lamansys.cart.infrastructure.input.rest.cart.adapter.CartAdapter;
import ar.lamansys.cart.infrastructure.input.rest.cart.dto.response.CartIdDTO;
import ar.lamansys.cart.infrastructure.input.rest.cart.dto.response.CartWithIdDTO;
import ar.lamansys.cart.infrastructure.input.rest.productcart.adapter.ProductAdapter;
import ar.lamansys.cart.infrastructure.input.rest.productcart.dto.request.ProductCartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
     CartGetter cartGetter;

    @Autowired
    CartCreator cartCreator;

    @Autowired
    ProductAdder productAdder;

    @Autowired
    ProductDeleter productDeleter;

    @Autowired
    TotalPriceGetter totalPriceGetter;

    @Autowired
    CartCheckout cartCheckout;

    @Autowired
    ProductAdapter productAdapter;

    @Autowired
    CartAdapter cartAdapter;





    @GetMapping("/{userId}")
    public CartWithIdDTO getCart(@PathVariable String userId ) {
        return cartAdapter.toCartWithIdDTO(cartGetter.run(userId));
    }

    @PostMapping("/{userId}")
    CartIdDTO createCart(@PathVariable final String userId, @RequestBody final ProductCartDTO productCartDTO){
        final ProductCartBo productCartBo = productAdapter.toProductCartBo(productCartDTO);
        final CartBo cartBo= new CartBo(userId);
        final Long id = cartCreator.run(cartBo, productCartBo);
        return new CartIdDTO(id);
    }

    @PostMapping("addProduct/{userId}")
    CartIdDTO addProduct(@PathVariable final String userId, @RequestBody final ProductCartDTO productCartDTO){
        final ProductCartBo productCartBo= productAdapter.toProductCartBo(productCartDTO);
        final Long id= productAdder.run(userId,productCartBo.getId(),productCartBo.getQuantity()).get().getId();
        return new CartIdDTO(id);
    }

    @DeleteMapping("deleteProduct/{userId}")
    CartIdDTO deleteProduct(@PathVariable final String userId, @RequestBody final ProductCartDTO productCartDTO){
        final ProductCartBo productCartBo= productAdapter.toProductCartBo(productCartDTO);
        final Long id= productDeleter.run(userId,productCartBo.getId(),productCartBo.getQuantity()).get().getId();
        return new CartIdDTO(id);
    }

    @GetMapping("totalPrice/{userId}")
    double CartPrice(@PathVariable final String userId){
        double price = totalPriceGetter.run(userId);
     return price;}

    @GetMapping("checkCart/{userId}")
    CartWithIdDTO getCartCheckout(@PathVariable final String userId){
        return cartAdapter.toCartWithIdDTO( cartCheckout.run(userId) );
    }
}
