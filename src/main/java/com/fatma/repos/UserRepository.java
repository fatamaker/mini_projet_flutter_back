package com.fatma.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fatma.entities.User;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	
	
	Optional<User> findByEmailUserAndPassword(String emailUser, String password);
	 List<User> findByNameUserContaining(String name);
	    
	    // Recherche par email
	    List<User> findByEmailUserContaining(String email);
	    
	    // Recherche par adresse
	    List<User> findByAdresseUserContaining(String adresse);
	    
	    // Recherche combinée
	    @Query("SELECT u FROM User u WHERE " +
	           "(u.nameUser LIKE %?1% OR ?1 IS NULL) AND " +
	           "(u.emailUser LIKE %?2% OR ?2 IS NULL) AND " +
	           "(u.adresseUser LIKE %?3% OR ?3 IS NULL)")
	    List<User> searchUsers(String name, String email, String adresse);
}
