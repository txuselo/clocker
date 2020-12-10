package com.redworks.clocker.controller;

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
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.access.prepost.PreAuthorize;

import com.redworks.clocker.service.UserService;
import com.redworks.clocker.persistence.entities.User;


@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_admin')")
	public ResponseEntity<User> getUserService(@PathVariable("id") Long id) {
		return new ResponseEntity<User> ( userService.findById(id), HttpStatus.OK);
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

	@GetMapping("/list")
	@PreAuthorize("hasAnyAuthority('ROLE_admin')")
	public ResponseEntity<List<User>> listAllUser() {
		return new ResponseEntity<List<User>> (userService.findAllUser(), HttpStatus.OK);
	}
	
	@PostMapping("/list")
	@PreAuthorize("hasAnyAuthority('ROLE_admin')")
	public ResponseEntity<List<User>> createListUser(@RequestBody List<User> listUser) {
		return new ResponseEntity<List<User>>(userService.saveListUser(listUser), HttpStatus.CREATED);
	}
	
	@CrossOrigin(methods=RequestMethod.PUT)
	@PreAuthorize("hasAnyAuthority('ROLE_admin')")
	@PutMapping("/list")
	public ResponseEntity<List<User>> updateListUser(@RequestBody List<User> listUser){
		return new ResponseEntity<List<User>>(userService.updateListUser(listUser), HttpStatus.OK);
	}
	
	@CrossOrigin(methods=RequestMethod.DELETE)
	@PreAuthorize("hasAnyAuthority('ROLE_admin')")
	@DeleteMapping("/list")
	public ResponseEntity<Void> deleteListUser(@RequestBody List<User> listUser){
		userService.deleteListUser(listUser);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
