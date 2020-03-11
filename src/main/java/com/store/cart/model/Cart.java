package com.store.cart.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Cart {
    @Id
    @GeneratedValue
    private Long id;
    private Double totalPrice;
}
