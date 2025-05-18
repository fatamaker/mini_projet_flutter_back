package com.fatma.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatma.entities.OrderLine;
@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
    
}
