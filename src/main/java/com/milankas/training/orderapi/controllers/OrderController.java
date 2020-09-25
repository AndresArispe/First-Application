package com.milankas.training.orderapi.controllers;

import com.milankas.training.orderapi.dto.OrderDto;
import com.milankas.training.orderapi.dto.patch.OrderPatchDto;
import com.milankas.training.orderapi.errors.ErrorResponse;
import com.milankas.training.orderapi.persistance.repository.AddressRepository;
import com.milankas.training.orderapi.persistance.repository.LineItemRepository;
import com.milankas.training.orderapi.persistance.repository.OrderRepository;
import com.milankas.training.orderapi.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class OrderController {


    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService= orderService;
    }

    @GetMapping("/v1/orders")
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/v1/orders/{orderId}")
    public OrderDto getOrder(@PathVariable UUID orderId) {
        if (orderService.getOrderById(orderId) == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "product not found"
            );
        }
        else {
            return orderService.getOrderById(orderId);
        }
    }

    @PostMapping("/v1/orders")
    public ResponseEntity<Object> postOrder(@Valid @RequestBody OrderDto orderDto, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
            return ResponseEntity.badRequest().body(new ErrorResponse("400", "Validation Failure", errors));
        }
        else {
            return ResponseEntity.ok(orderService.saveOrder(orderDto));
        }
    }

    @DeleteMapping("/v1/orders/{orderId}")
    public ResponseEntity deleteOrder(@PathVariable UUID orderId) {
        if (orderService.deleteOrder(orderId) == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "product not found"
            );
        }
        else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PatchMapping("/v1/orders/{orderId}")
    public ResponseEntity<Object> patchOrder(@PathVariable UUID orderId,@Valid @RequestBody OrderPatchDto orderPatchDto, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
            return ResponseEntity.badRequest().body(new ErrorResponse("400", "Validation Failure", errors));
        }
        else {
            if (orderService.getOrderById(orderId) == null) {
                return ResponseEntity.notFound().build();
            }
            else {
                return ResponseEntity.ok(orderService.updateOrder(orderPatchDto, orderId));
            }
        }
    }
}
