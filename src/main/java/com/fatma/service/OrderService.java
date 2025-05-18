package com.fatma.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatma.entities.Article;
import com.fatma.entities.Order;
import com.fatma.entities.OrderLine;
import com.fatma.entities.User;
import com.fatma.repos.ArticleRepository;
import com.fatma.repos.OrderLineRepository;
import com.fatma.repos.OrderRepository;
import com.fatma.repos.UserRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderLineRepository orderLineRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ArticleRepository articleRepository;



  

        public Order createOrderWithLines(Order order, List<OrderLine> lines, Long userId) {
            // Récupérer l'utilisateur
            User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

            // Associer l'utilisateur à la commande
            order.setUser(user);
            order.setStatus("pending");
            order.setQuantite(0);
            order.setTotal(0.0);

            // Enregistrer l'ordre d'abord pour obtenir un ID
            Order savedOrder = orderRepository.save(order);

            double total = 0.0;
            int totalQuantite = 0;
            for (OrderLine line : lines) {
                if (line.getArticle() == null || line.getArticle().getId() == null) {
                    throw new RuntimeException("Article ID is required for each order line.");
                }

                Article article = articleRepository.findById(line.getArticle().getId())
                    .orElseThrow(() -> new RuntimeException("Article not found"));

                line.setArticle(article);
                line.setOrder(savedOrder);

                total += line.getQuantity() * article.getPrix();
                totalQuantite += line.getQuantity();

                orderLineRepository.save(line);
                savedOrder.getOrderLines().add(line);
            }


            savedOrder.setTotal(total);
            savedOrder.setQuantite(totalQuantite);

            return orderRepository.save(savedOrder); // mise à jour avec les totaux et lignes
        }
   


    // Méthode pour obtenir toutes les commandes
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Méthode pour obtenir une commande par ID
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    // Méthode pour supprimer une commande
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}