package com.milankas.training.orderapi.services;

import com.milankas.training.orderapi.dto.OrderDto;
import com.milankas.training.orderapi.dto.patch.OrderPatchDto;
import com.milankas.training.orderapi.mappers.AddressMapper;
import com.milankas.training.orderapi.mappers.LineItemMapper;
import com.milankas.training.orderapi.mappers.OrderMapper;
import com.milankas.training.orderapi.persistance.model.Address;
import com.milankas.training.orderapi.persistance.model.LineItem;
import com.milankas.training.orderapi.persistance.model.Order;
import com.milankas.training.orderapi.persistance.repository.AddressRepository;
import com.milankas.training.orderapi.persistance.repository.LineItemRepository;
import com.milankas.training.orderapi.persistance.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private AddressRepository addressRepository;
    private LineItemRepository lineItemRepository;
    private OrderRepository orderRepository;
    private OrderMapper orderMapper;
    private LineItemMapper lineItemMapper;
    private AddressMapper addressMapper;

    public OrderService(AddressRepository addressRepository, LineItemRepository lineItemRepository, OrderRepository orderRepository, OrderMapper orderMapper, LineItemMapper lineItemMapper, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.lineItemRepository = lineItemRepository;
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.lineItemMapper = lineItemMapper;
        this.addressMapper = addressMapper;
    }

    public OrderDto saveOrder(OrderDto orderDto) {
        Order newOrder = new Order();
        newOrder.setUserId(orderDto.getUserId());
        newOrder.setEmailAddress(orderDto.getEmailAddress());
        orderRepository.save(newOrder);
        Address newAddress = addressMapper.toAddress(orderDto.getAddress());
        newAddress.setOrder(newOrder);
        newOrder.setAddress(newAddress);
        addressRepository.save(newAddress);
        orderRepository.save(newOrder);
        List<LineItem> newLineItems = lineItemMapper.toLineItems(orderDto.getLineItems());
        for(int i = 0; i < newLineItems.size(); i++) {
            newLineItems.get(i).setOrder(newOrder);
            newOrder.getLineItems().add(newLineItems.get(i));
            lineItemRepository.save(newLineItems.get(i));
            orderRepository.save(newOrder);
        }
        return orderMapper.orderToDto(newOrder);
    }

    public OrderDto deleteOrder(UUID id) {
        Order deletedOrder = orderRepository.findById(id).orElse(null);
        if (deletedOrder == null) {
            return null;
        }
        else {
            orderRepository.deleteById(id);
            return orderMapper.orderToDto(deletedOrder);
        }
    }

    public OrderDto getOrderById(UUID id) {
        return orderMapper.orderToDto(orderRepository.findById(id).orElse(null));
    }

    public List<OrderDto> getAllOrders() {
        return orderMapper.toOrdersDto(orderRepository.findAll());
    }

    public OrderDto updateOrder(OrderPatchDto orderPatchDto, UUID orderId) {
        Order existingOrder = orderRepository.findById(orderId).orElse(null);
        if (existingOrder == null) {
            return null;
        }
        else {
            if (orderPatchDto.getUserId() != null) {
                existingOrder.setUserId(orderPatchDto.getUserId());
            }
            if (orderPatchDto.getEmailAddress() != null) {
                existingOrder.setEmailAddress(orderPatchDto.getEmailAddress());
            }
            if (orderPatchDto.getAddress() != null) {
                existingOrder.getAddress().setAddressLine1(orderPatchDto.getAddress().getAddressLine1());
                existingOrder.getAddress().setAddressLine2(orderPatchDto.getAddress().getAddressLine2());
                existingOrder.getAddress().setContactName(orderPatchDto.getAddress().getContactName());
                existingOrder.getAddress().setContactPhoneNumber(orderPatchDto.getAddress().getContactPhoneNumber());
                existingOrder.getAddress().setState(orderPatchDto.getAddress().getState());
                existingOrder.getAddress().setCity(orderPatchDto.getAddress().getCity());
                existingOrder.getAddress().setZipCode(orderPatchDto.getAddress().getZipCode());
                existingOrder.getAddress().setCountryCode(orderPatchDto.getAddress().getCountryCode());
            }
            if ((orderPatchDto.getLineItems() != null) && (orderPatchDto.getLineItems().size() > 0)) {
                for(int i = (existingOrder.getLineItems().size() - 1); i >=0; i--) {
                    UUID id = existingOrder.getLineItems().get(i).getLineItemId();
                    existingOrder.getLineItems().remove(i);
                    lineItemRepository.deleteById(id);
                }
                List<LineItem> newLineItems = lineItemMapper.toLineItems(orderPatchDto.getLineItems());
                for(int i = 0; i < newLineItems.size(); i++) {
                    newLineItems.get(i).setOrder(existingOrder);
                    existingOrder.getLineItems().add(newLineItems.get(i));
                    lineItemRepository.save(newLineItems.get(i));
                    orderRepository.save(existingOrder);
                }
            }
        }
        return orderMapper.orderToDto(orderRepository.save(existingOrder));
    }
}
