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

import com.redworks.clocker.service.RecordService;
import com.redworks.clocker.persistence.entities.Record;


@RestController
@RequestMapping("/records")
public class RecordController {

	@Autowired
	RecordService recordService;

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_admin','ROLE_user')")
	public ResponseEntity<Record> getRecordService(@PathVariable("id") Long id) {
		return new ResponseEntity<Record> ( recordService.findById(id), HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasAnyAuthority('ROLE_admin','ROLE_user')")
	public ResponseEntity<Record> createRecord(@RequestBody Record record) {
		return new ResponseEntity<Record>(recordService.saveRecord(record), HttpStatus.CREATED);
	}
	
	@CrossOrigin(methods=RequestMethod.PUT)
	@PreAuthorize("hasAnyAuthority('ROLE_admin','ROLE_user')")
	@PutMapping("/{id}")
	public ResponseEntity<Record> updateRecord(@PathVariable("id") Long id, @RequestBody Record record){
		return new ResponseEntity<Record>(recordService.updateRecord(id, record), HttpStatus.OK);
	}
	
	@CrossOrigin(methods=RequestMethod.DELETE)
	@PreAuthorize("hasAnyAuthority('ROLE_admin','ROLE_user')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRecord(@PathVariable("id") Long id){
		recordService.deleteRecord(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping
	@PreAuthorize("hasAnyAuthority('ROLE_admin','ROLE_user')")
	public ResponseEntity<List<Record>> listAllRecord() {
		return new ResponseEntity<List<Record>> (recordService.findAllRecord(), HttpStatus.OK);
	}
	
}
