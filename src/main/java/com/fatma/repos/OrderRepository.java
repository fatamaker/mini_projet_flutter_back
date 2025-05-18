package com.fatma.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fatma.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	  List<Order> findByStatusAndUser_NameUserContainingIgnoreCase(String status, String nameUser);
	    
	    List<Order> findByUser_IdUser(Long userId);
	 // Ajoutez cette méthode dans OrderRepository.java
	    List<Order> findByStatusAndUser_IdUser(String status, Long idUser);
}
