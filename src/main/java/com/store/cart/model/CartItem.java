package com.store.cart.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class CartItem {
    @Id
    private Long productId;
    private int quantity;
    @ManyToOne
    private Cart cart;
}
