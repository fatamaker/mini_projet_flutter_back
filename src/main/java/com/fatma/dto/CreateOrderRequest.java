package com.fatma.dto;


import com.fatma.entities.Order;
import com.fatma.entities.OrderLine;
import java.util.List;

public class CreateOrderRequest {

    private Order order;
    private List<OrderLine> orderLines;

    // Getters and Setters
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }
}
