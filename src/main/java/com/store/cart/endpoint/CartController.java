package com.store.cart.endpoint;

import com.store.cart.model.Cart;
import com.store.cart.model.CartItem;
import com.store.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public List<Cart> getAllCarts(){
        return cartService.getAllCarts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody Cart cart) {
        return cartService.create(cart);
    }

    @PostMapping("/{id}/lines")
    public void addToCart(@PathVariable("id") Long cartId, @RequestBody CartItem cartItem){
        cartService.addToCart(cartId,cartItem);
    }

    @GetMapping("/{id}/lines")
    public Cart getCalculatedCart(@PathVariable("id") Long cartId){
        return cartService.getCartWithCalculatedPrice(cartId);
    }
}
