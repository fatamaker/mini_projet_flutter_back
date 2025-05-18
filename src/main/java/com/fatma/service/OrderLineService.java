package com.fatma.service;


import com.fatma.entities.OrderLine;
import com.fatma.repos.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderLineService {

    @Autowired
    private OrderLineRepository orderLineRepository;

    // Sauvegarder une ligne de commande
    public OrderLine createOrderLine(OrderLine orderLine) {
        return orderLineRepository.save(orderLine);
    }

    // Méthode pour obtenir une ligne de commande par ID
    public OrderLine getOrderLineById(Long id) {
        return orderLineRepository.findById(id).orElse(null);
    }
}
