package com.store.cart.service;


import com.store.cart.model.Cart;
import com.store.cart.model.CartItem;

import java.util.List;

public interface CartService {
    List<Cart> getAllCarts();
    Long create(Cart cart);
    void addToCart(Long cartId, CartItem cartItem);
    Cart getCartWithCalculatedPrice(Long cartId);
}
