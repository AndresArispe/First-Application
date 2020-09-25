package com.milankas.training.orderapi.mappers;

import com.milankas.training.orderapi.dto.LineItemDto;
import com.milankas.training.orderapi.persistance.model.LineItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LineItemMapper {

    LineItemDto lineItemToDto(LineItem lineItem);

    List<LineItemDto> toLineItemsDto(List<LineItem> lineItems);

    LineItem toLineItem(LineItemDto lineItemDto);

    List<LineItem> toLineItems(List<LineItemDto> lineItemsDto);
}
