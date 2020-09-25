package com.milankas.training.orderapi.dto;

import com.milankas.training.orderapi.persistance.model.Address;
import com.milankas.training.orderapi.persistance.model.LineItem;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class OrderDto {

    private UUID id;

    @NotNull(message = "User id it's required")
    private UUID userId;

    @Email(message = "Must be a valid email address")
    @NotNull(message = "Email address it's required")
    private String emailAddress;

    @Valid
    @NotNull(message = "Address it's required")
    private AddressDto address;

    @Valid
    @NotNull(message = "Line items are required")
    @NotEmpty(message = "You need at least one line item")
    private List<LineItemDto> lineItems = new ArrayList<>();
}
