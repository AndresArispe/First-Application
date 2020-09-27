package com.milankas.training.orderapi.dto.in;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.milankas.training.orderapi.persistance.model.Order;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class LineItemDto {

    private UUID lineItemId;

    @NotNull(message = "Product id it's required")
    private UUID productId;

    @NotNull(message = "Quantity it's required")
    @Min(value = 1, message = "The minimum quantity it's 1")
    private int qty;

    @JsonIgnore
    private Order order;

}
