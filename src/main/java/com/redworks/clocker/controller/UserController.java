package com.redworks.clocker.controller;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.access.prepost.PreAuthorize;

import com.redworks.clocker.service.UserService;
import com.redworks.clocker.persistence.entities.User;


@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_admin')")
	public ResponseEntity<User> getUserService(@PathVariable("id") Long id) {
		User user = userService.findById(id);
		return user != null ? new ResponseEntity<User> (user, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	@PreAuthorize("hasAnyAuthority('ROLE_admin')")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
	}
	
	@CrossOrigin(methods=RequestMethod.PUT)
	@PreAuthorize("hasAnyAuthority('ROLE_admin')")
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user){
		return new ResponseEntity<User>(userService.updateUser(id, user), HttpStatus.OK);
	}
	
	@CrossOrigin(methods=RequestMethod.DELETE)
	@PreAuthorize("hasAnyAuthority('ROLE_admin')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id){
		userService.deleteUser(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping
	@PreAuthorize("hasAnyAuthority('ROLE_admin')")
	public ResponseEntity<List<User>> findUsers(@RequestParam(name="username", required = false) String username) {
		if (username == null || username.isEmpty()){
			return new ResponseEntity<List<User>> (userService.findAllUser(), HttpStatus.OK);
		}
		List<User> response = new ArrayList<>();
		User user = userService.findByUsername(username);
		
		if (user != null){
			response.add(user);
		}
		
		return new ResponseEntity<List<User>> (response, HttpStatus.OK); 
		
	}
	
}
