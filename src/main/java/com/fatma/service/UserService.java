package com.fatma.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatma.entities.User;
import com.fatma.repos.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userrepository;
	
	
	public List<User> getAllUsers()
	{
		return userrepository.findAll();
	}
	public void deleteUser(Long id)
	{
		userrepository.deleteById(id);
	}
	public User createUser(User user)
	{
		return userrepository.save(user);
	}
	public User updateUser(Long id, User user) {
	    return userrepository.findById(id).map(existingUser -> {
	        existingUser.setNameUser(user.getNameUser());
	        existingUser.setEmailUser(user.getEmailUser());
	        existingUser.setAdresseUser(user.getAdresseUser());
	        existingUser.setRole(user.getRole());      
	        existingUser.setPassword(user.getPassword());
	        return userrepository.save(existingUser);
	    }).orElse(null); 
	}
	public User login(String email, String password) {
	    return userrepository.findByEmailUserAndPassword(email, password)
	            .orElse(null);
	}
	public User getUserById(Long id) {
	    return userrepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
	}
	public List<User> searchUsers(String name, String email, String adresse) {
	    return userrepository.searchUsers(name, email, adresse);
	}

	public List<User> findByNameContaining(String name) {
	    return userrepository.findByNameUserContaining(name);
	}

	public List<User> findByEmailContaining(String email) {
	    return userrepository.findByEmailUserContaining(email);
	}

	public List<User> findByAdresseContaining(String adresse) {
	    return userrepository.findByAdresseUserContaining(adresse);
	}


	
	

	
	

}
