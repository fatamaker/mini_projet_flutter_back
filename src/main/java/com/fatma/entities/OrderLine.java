package com.fatma.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "order_lines")
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    
    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;
 

    public OrderLine() {}

    public OrderLine(int quantity, Article article, Order order) {
        this.quantity = quantity;
        this.article = article;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderLine [id=" + id + ", quantity=" + quantity + ", article=" + article + "]";
    }
}
