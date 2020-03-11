package com.store.cart.service;

import com.store.cart.model.Cart;
import com.store.cart.model.CartItem;
import com.store.cart.model.Product;
import com.store.cart.repository.CartItemRepository;
import com.store.cart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;

    final String uri = "http://localhost:8080/products/";

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository= cartItemRepository;
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Long create(Cart cart) {
        return cartRepository.save(cart).getId();
    }

    @Override
    public void addToCart(Long cartId, CartItem cartItem) {
        Cart cart = cartRepository.findById(cartId).orElse(new Cart());
        cartRepository.save(cart);
        cartItem.setCart(cart);
        cartItemRepository.save(cartItem);
    }

    @Override
    public Cart getCartWithCalculatedPrice(Long cartId) {
        Cart cart = cartRepository.findById(cartId).get();
        List<CartItem> cartItems = cartItemRepository.findAllByCart(cart);
        RestTemplate restTemplate = new RestTemplate();
        double totalPrice=0;
        for (CartItem cartItem:cartItems) {
            Product result = restTemplate.getForObject(uri+cartItem.getProductId(), Product.class);
            totalPrice+=result.getPrice()*cartItem.getQuantity();
        }
        cart.setTotalPrice(totalPrice);
        cartRepository.save(cart);
        return cart;
    }
}
