package com.fatma.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fatma.entities.Article;
import com.fatma.entities.User;
import com.fatma.service.ArticleService;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/articles")
public class ArticleRestController {
	@Autowired 
	ArticleService articleservice;
	
	
	
	@GetMapping
	public List<Article> getAllArticles()
	{
		return articleservice.getAllArticles();
	}
	
	@DeleteMapping("/{id}")
	public void deleteArticle(@PathVariable Long id)
	{
		articleservice.deleteArticle(id);
	}
	@PutMapping("/{id}")
	public Article updateArticle(@PathVariable Long id, @RequestBody Article a)
	{
		return articleservice.updateArticle(id, a);
	}
	@PostMapping("/create")
	public ResponseEntity<Article> createArticleWithImage(
	        @RequestParam("nom") String nom,
	        @RequestParam("description") String description,
	        @RequestParam("prix") Long prix,
	        @RequestParam("image") MultipartFile imageFile) {

	    String fileName = imageFile.getOriginalFilename();

	    // Chemin absolu vers dossier uploads
	    String uploadDir = System.getProperty("user.dir") + "/uploads/";
	    Path uploadPath = Paths.get(uploadDir);

	    try {
	        if (!Files.exists(uploadPath)) {
	            Files.createDirectories(uploadPath); // Créer le dossier s'il n'existe pas
	        }

	        Path filePath = uploadPath.resolve(fileName);
	        Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
	    } catch (IOException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }

	    Article article = new Article();
	    article.setNom(nom);
	    article.setDescription(description);
	    article.setPrix(prix);
	    article.setImage(fileName); // On enregistre juste le nom de fichier

	    Article savedArticle = articleservice.saveArticle(article);
	    return ResponseEntity.ok(savedArticle);
	}


    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {
        Optional<Article> article = articleservice.getArticleById(id);
        return article.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/search")
    public List<Article> searchArticles(
        @RequestParam(required = false) String nom,
        @RequestParam(required = false) Long prixMin,
        @RequestParam(required = false) Long prixMax) {
        
        if (nom != null && prixMin != null && prixMax != null) {
            return articleservice.findByNomAndPrixBetween(nom, prixMin, prixMax);
        } else if (nom != null) {
            return articleservice.findByNom(nom);
        } else if (prixMin != null && prixMax != null) {
            return articleservice.findByPrixBetween(prixMin, prixMax);
        } else {
            return articleservice.getAllArticles();
        }
    }
    
    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        Path filePath = Paths.get("src/main/resources/static/images/" + fileName);

        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            // Save the image URL or file path to the database
            articleservice.saveImagePath(fileName);
            return ResponseEntity.ok("File uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
    }

	

}
