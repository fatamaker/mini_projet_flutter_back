package com.fatma.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fatma.dto.CreateOrderRequest;
import com.fatma.entities.Order;
import com.fatma.entities.OrderLine;
import com.fatma.repos.OrderRepository;
import com.fatma.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderRestController {

    @Autowired
    private OrderService orderService;
    @PostMapping("/createWithLines")
    public ResponseEntity<Order> createOrderWithLines(@RequestBody CreateOrderRequest request,
                                                      @RequestParam Long userId) {
        Order order = request.getOrder();
        List<OrderLine> lines = request.getOrderLines();

        Order savedOrder = orderService.createOrderWithLines(order, lines, userId);
        return ResponseEntity.ok(savedOrder);
    }
    // Obtenir toutes les commandes
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // Obtenir une commande par ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return order != null ? ResponseEntity.ok(order) : ResponseEntity.notFound().build();
    }

    // Supprimer une commande
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        Order updatedOrder = orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok(updatedOrder);
    }
}