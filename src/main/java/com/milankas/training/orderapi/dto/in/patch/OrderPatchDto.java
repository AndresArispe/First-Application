package com.milankas.training.orderapi.dto.in.patch;

import com.milankas.training.orderapi.dto.in.LineItemDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class OrderPatchDto {

    private UUID id;

    private UUID userId;

    @Email(message = "Must be a valid email address")
    private String emailAddress;

    @Valid
    private AddressPatchDto address;

    @Valid
    private List<LineItemDto> lineItems = new ArrayList<>();
}
