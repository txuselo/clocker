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

import com.redworks.clocker.service.RoleService;
import com.redworks.clocker.persistence.entities.Role;


@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	RoleService roleService;

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_admin')")
	public ResponseEntity<Role> getRoleService(@PathVariable("id") Long id) {
		return new ResponseEntity<Role> ( roleService.findById(id), HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasAnyAuthority('ROLE_admin')")
	public ResponseEntity<Role> createRole(@RequestBody Role role) {
		return new ResponseEntity<Role>(roleService.saveRole(role), HttpStatus.CREATED);
	}
	
	@CrossOrigin(methods=RequestMethod.PUT)
	@PreAuthorize("hasAnyAuthority('ROLE_admin')")
	@PutMapping("/{id}")
	public ResponseEntity<Role> updateRole(@PathVariable("id") Long id, @RequestBody Role role){
		return new ResponseEntity<Role>(roleService.updateRole(id, role), HttpStatus.OK);
	}
	
	@CrossOrigin(methods=RequestMethod.DELETE)
	@PreAuthorize("hasAnyAuthority('ROLE_admin')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRole(@PathVariable("id") Long id){
		roleService.deleteRole(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("/list")
	@PreAuthorize("hasAnyAuthority('ROLE_admin')")
	public ResponseEntity<List<Role>> listAllRole() {
		return new ResponseEntity<List<Role>> (roleService.findAllRole(), HttpStatus.OK);
	}
	
	@PostMapping("/list")
	@PreAuthorize("hasAnyAuthority('ROLE_admin')")
	public ResponseEntity<List<Role>> createListRole(@RequestBody List<Role> listRole) {
		return new ResponseEntity<List<Role>>(roleService.saveListRole(listRole), HttpStatus.CREATED);
	}
	
	@CrossOrigin(methods=RequestMethod.PUT)
	@PreAuthorize("hasAnyAuthority('ROLE_admin')")
	@PutMapping("/list")
	public ResponseEntity<List<Role>> updateListRole(@RequestBody List<Role> listRole){
		return new ResponseEntity<List<Role>>(roleService.updateListRole(listRole), HttpStatus.OK);
	}
	
	@CrossOrigin(methods=RequestMethod.DELETE)
	@PreAuthorize("hasAnyAuthority('ROLE_admin')")
	@DeleteMapping("/list")
	public ResponseEntity<Void> deleteListRole(@RequestBody List<Role> listRole){
		roleService.deleteListRole(listRole);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
