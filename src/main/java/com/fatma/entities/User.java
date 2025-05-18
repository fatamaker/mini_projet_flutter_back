package com.fatma.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity; 
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

 
   
    private String nameUser;

    @Column(unique = true)
    private String emailUser;

    
   
    private String adresseUser;

    private String role = "client"; 

    private String password;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String nameUser, String emailUser, String adresseUser, String role, String password) {
		super();
		this.nameUser = nameUser;
		this.emailUser = emailUser;
		this.adresseUser = adresseUser;
		this.role = role;
		this.password = password;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public String getAdresseUser() {
		return adresseUser;
	}

	public void setAdresseUser(String adresseUser) {
		this.adresseUser = adresseUser;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [nameUser=" + nameUser + ", emailUser=" + emailUser + ", adresseUser=" + adresseUser + ", role="
				+ role + ", password=" + password + "]";
	}

	

    
   
}