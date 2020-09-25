package com.milankas.training.orderapi.mappers;

import com.milankas.training.orderapi.dto.OrderDto;
import com.milankas.training.orderapi.persistance.model.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDto orderToDto(Order order);

    List<OrderDto> toOrdersDto(List<Order> orders);

    Order toOrder(OrderDto orderDto);
}
