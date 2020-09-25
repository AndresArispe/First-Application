package com.milankas.training.orderapi.persistance.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID userId;
    private String emailAddress;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<LineItem> lineItems = new ArrayList<>();

}
