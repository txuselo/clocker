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

import com.redworks.clocker.service.EmployeeService;
import com.redworks.clocker.persistence.entities.Employee;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_admin','ROLE_user')")
	public ResponseEntity<Employee> getEmployeeService(@PathVariable("id") Long id) {
		return new ResponseEntity<Employee> ( employeeService.findById(id), HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasAnyAuthority('ROLE_admin')")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
	}
	
	@CrossOrigin(methods=RequestMethod.PUT)
	@PreAuthorize("hasAnyAuthority('ROLE_admin','ROLE_user')")
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee){
		return new ResponseEntity<Employee>(employeeService.updateEmployee(id, employee), HttpStatus.OK);
	}
	
	@CrossOrigin(methods=RequestMethod.DELETE)
	@PreAuthorize("hasAnyAuthority('ROLE_admin')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long id){
		employeeService.deleteEmployee(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("/list")
	@PreAuthorize("hasAnyAuthority('ROLE_admin')")
	public ResponseEntity<List<Employee>> listAllEmployee() {
		return new ResponseEntity<List<Employee>> (employeeService.findAllEmployee(), HttpStatus.OK);
	}
	
	@PostMapping("/list")
	@PreAuthorize("hasAnyAuthority('ROLE_admin')")
	public ResponseEntity<List<Employee>> createListEmployee(@RequestBody List<Employee> listEmployee) {
		return new ResponseEntity<List<Employee>>(employeeService.saveListEmployee(listEmployee), HttpStatus.CREATED);
	}
	
	@CrossOrigin(methods=RequestMethod.PUT)
	@PreAuthorize("hasAnyAuthority('ROLE_admin')")
	@PutMapping("/list")
	public ResponseEntity<List<Employee>> updateListEmployee(@RequestBody List<Employee> listEmployee){
		return new ResponseEntity<List<Employee>>(employeeService.updateListEmployee(listEmployee), HttpStatus.OK);
	}
	
	@CrossOrigin(methods=RequestMethod.DELETE)
	@PreAuthorize("hasAnyAuthority('ROLE_admin')")
	@DeleteMapping("/list")
	public ResponseEntity<Void> deleteListEmployee(@RequestBody List<Employee> listEmployee){
		employeeService.deleteListEmployee(listEmployee);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
