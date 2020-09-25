package com.milankas.training.orderapi.persistance.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "line_item")
public class LineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID lineItemId;

    private UUID productId;
    private int qty;

    @JsonIgnore
    @ManyToOne
    private Order order;
}
