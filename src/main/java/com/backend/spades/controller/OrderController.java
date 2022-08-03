package com.backend.spades.controller;

import com.backend.spades.exception.NotFoundException;
import com.backend.spades.model.Order;
import com.backend.spades.model.OrderItem;
import com.backend.spades.repository.OrderItemRepository;
import com.backend.spades.repository.OrderRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @PostMapping("/{orderId}/orderItem")
    public void addToOrder(@PathVariable long orderId, @RequestBody OrderItem orderItem) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isEmpty()) {
            throw new NotFoundException("Order with id: " + orderId + "not found");
        }
        Order order = optionalOrder.get();
        List<OrderItem> orderItems = order.getOrderItems();
        int index = orderItems.indexOf(orderItem);
        if (index == -1) {
            orderItem.setOrder(order);
            orderItems.add(orderItem);
        } else {
            OrderItem existedOrderItem = orderItems.get(index);
            existedOrderItem.setQuantity(existedOrderItem.getQuantity() + orderItem.getQuantity());
        }
        orderItemRepository.saveAll(orderItems);
    }

    @DeleteMapping("/orderItem/{orderItemId}")
    public void deleteProductFromOrder(@PathVariable long orderItemId) {
        orderItemRepository.deleteById(orderItemId);
    }

    @DeleteMapping("/{orderId}")
    public void delete(@PathVariable long orderId) {
        orderRepository.deleteById(orderId);
    }
}
