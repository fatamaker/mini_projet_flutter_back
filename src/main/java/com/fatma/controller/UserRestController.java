package com.fatma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.fatma.dto.UserDto;
import com.fatma.entities.User;
import com.fatma.service.UserService;

/*@CrossOrigin(origins = "http://localhost:4200")*/
@RestController
@RequestMapping("/api/users")
public class UserRestController {
	@Autowired
	private UserService uservice;
	
	@GetMapping
	public List<User> getAllUsers()
	{
		 return uservice.getAllUsers();
	}
	
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Long id)
	{
		uservice.deleteUser(id);
	}
	
	@PostMapping
	public User createUser(@RequestBody User user)
	{
		 user.setIdUser(null);
		return uservice.createUser(user);
	}
	
	@PutMapping("/{id}")
	public User updateUser(@PathVariable Long id, @RequestBody User user) {
	    return uservice.updateUser(id, user);
	    }
	@PostMapping("/login")
	public User login(@RequestBody UserDto userDto) {
	    return uservice.login(userDto.getEmail(), userDto.getPassword());
	}
	@GetMapping("/{id}")
	public User getUserById(@PathVariable Long id) {
	    return uservice.getUserById(id);
	}
	@GetMapping("/search")
	public List<User> searchUsers(
	    @RequestParam(required = false) String name,
	    @RequestParam(required = false) String email,
	    @RequestParam(required = false) String adresse) {
	    
	    return uservice.searchUsers(name, email, adresse);
	}

	
	}
