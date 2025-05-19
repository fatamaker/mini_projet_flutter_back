package com.fatma.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity 
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nom;
	private String description;
	private String image;
	private Long prix;

	// ✅ Ajout du stock
	private int stock;

	// Getters & Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public Long getPrix() {
		return prix;
	}
	public void setPrix(Long prix) {
		this.prix = prix;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	// ✅ Getter & Setter pour stock
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}

	// Constructeurs
	public Article() {
		super();
	}

	public Article(String nom, Long prix) {
		this.nom = nom;
		this.prix = prix;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", nom=" + nom + ", prix=" + prix + ", stock=" + stock + "]";
	}
}
