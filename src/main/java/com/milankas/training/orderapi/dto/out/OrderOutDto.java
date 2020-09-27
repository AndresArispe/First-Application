package com.milankas.training.orderapi.dto.out;

import com.milankas.training.orderapi.dto.in.AddressDto;
import com.milankas.training.orderapi.dto.in.LineItemDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class OrderOutDto {

    private UUID id;

    private UUID userId;
    private String emailAddress;
    private AddressDto address;
    private List<LineItemDto> lineItems = new ArrayList<>();
}
