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

import com.redworks.clocker.service.RelUserRoleService;
import com.redworks.clocker.persistence.entities.RelUserRole;


@RestController
@RequestMapping("/rel-user-role")
public class RelUserRoleController {

	@Autowired
	RelUserRoleService relUserRoleService;

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_admin','ROLE_user')")
	public ResponseEntity<RelUserRole> getRelUserRoleService(@PathVariable("id") Long id) {
		return new ResponseEntity<RelUserRole> ( relUserRoleService.findById(id), HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasAnyAuthority('ROLE_admin','ROLE_user')")
	public ResponseEntity<RelUserRole> createRelUserRole(@RequestBody RelUserRole relUserRole) {
		return new ResponseEntity<RelUserRole>(relUserRoleService.saveRelUserRole(relUserRole), HttpStatus.CREATED);
	}
	
	@CrossOrigin(methods=RequestMethod.PUT)
	@PreAuthorize("hasAnyAuthority('ROLE_admin','ROLE_user')")
	@PutMapping("/{id}")
	public ResponseEntity<RelUserRole> updateRelUserRole(@PathVariable("id") Long id, @RequestBody RelUserRole relUserRole){
		return new ResponseEntity<RelUserRole>(relUserRoleService.updateRelUserRole(id, relUserRole), HttpStatus.OK);
	}
	
	@CrossOrigin(methods=RequestMethod.DELETE)
	@PreAuthorize("hasAnyAuthority('ROLE_admin','ROLE_user')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRelUserRole(@PathVariable("id") Long id){
		relUserRoleService.deleteRelUserRole(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("/list")
	@PreAuthorize("hasAnyAuthority('ROLE_admin','ROLE_user')")
	public ResponseEntity<List<RelUserRole>> listAllRelUserRole() {
		return new ResponseEntity<List<RelUserRole>> (relUserRoleService.findAllRelUserRole(), HttpStatus.OK);
	}
	
	@PostMapping("/list")
	@PreAuthorize("hasAnyAuthority('ROLE_admin','ROLE_user')")
	public ResponseEntity<List<RelUserRole>> createListRelUserRole(@RequestBody List<RelUserRole> listRelUserRole) {
		return new ResponseEntity<List<RelUserRole>>(relUserRoleService.saveListRelUserRole(listRelUserRole), HttpStatus.CREATED);
	}
	
	@CrossOrigin(methods=RequestMethod.PUT)
	@PreAuthorize("hasAnyAuthority('ROLE_admin','ROLE_user')")
	@PutMapping("/list")
	public ResponseEntity<List<RelUserRole>> updateListRelUserRole(@RequestBody List<RelUserRole> listRelUserRole){
		return new ResponseEntity<List<RelUserRole>>(relUserRoleService.updateListRelUserRole(listRelUserRole), HttpStatus.OK);
	}
	
	@CrossOrigin(methods=RequestMethod.DELETE)
	@PreAuthorize("hasAnyAuthority('ROLE_admin','ROLE_user')")
	@DeleteMapping("/list")
	public ResponseEntity<Void> deleteListRelUserRole(@RequestBody List<RelUserRole> listRelUserRole){
		relUserRoleService.deleteListRelUserRole(listRelUserRole);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
