package com.fatma.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fatma.entities.Article;
@Repository
public interface ArticleRepository extends JpaRepository<Article,Long>  {
	
    List<Article> findByNomContaining(String nom);
    
    @Query("SELECT a FROM Article a WHERE a.prix BETWEEN ?1 AND ?2")
    List<Article> findByPrixBetween(Long prixMin, Long prixMax);
    
    @Query("SELECT a FROM Article a WHERE a.nom LIKE %?1% AND a.prix BETWEEN ?2 AND ?3")
    List<Article> findByNomAndPrixBetween(String nom, Long prixMin, Long prixMax);

}
