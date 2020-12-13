package com.redworks.clocker.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

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

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.redworks.clocker.service.RecordService;
import com.redworks.clocker.service.UserService;
import com.redworks.clocker.persistence.entities.Record;


@RestController
@RequestMapping("/records")
@Slf4j
public class RecordController {

	@Autowired
	RecordService recordService;

	@Autowired
	UserService userService;

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_user')")
	public ResponseEntity<Record> getRecordService(@PathVariable("id") Long id, Authentication authentication, HttpServletRequest request) {
		log.info("{} {}", authentication.getName(), authentication.getAuthorities() );
		Record response = recordService.findById(id);
		if (response == null || ! hasAuthority(response, authentication)){
			return new ResponseEntity<Record> (HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Record> ( response, HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasAnyAuthority('ROLE_user')")
	public ResponseEntity<Record> createRecord(@RequestBody Record record, Authentication authentication) {
		Record newRecord = new Record();
		newRecord.setUser(userService.findByUsername(authentication.getName()));
		return new ResponseEntity<Record>(recordService.saveRecord(newRecord), HttpStatus.CREATED);
	}
	
	@CrossOrigin(methods=RequestMethod.PUT)
	@PreAuthorize("hasAnyAuthority('ROLE_user')")
	@PutMapping("/{id}")
	public ResponseEntity<Record> updateRecord(@PathVariable("id") Long id, @RequestBody Record record, Authentication authentication){
		Record pathRecord = recordService.findById(id);
		if (pathRecord == null || ! hasAuthority(pathRecord, authentication)){
			return new ResponseEntity<Record> (HttpStatus.NOT_FOUND);
		}
		Record updated = recordService.partialUpdate(pathRecord, record);
		return new ResponseEntity<Record>(recordService.updateRecord(id, updated), HttpStatus.OK);
	}

	@CrossOrigin(methods=RequestMethod.PUT)
	@PreAuthorize("hasAnyAuthority('ROLE_user')")
	@PutMapping("/{id}/start")
	public ResponseEntity<Record> startRecord(@PathVariable("id") Long id, @RequestBody Record record, Authentication authentication){
		Record pathRecord = recordService.findById(id);
		if (pathRecord == null || ! hasAuthority(pathRecord, authentication)){
			return new ResponseEntity<Record> (HttpStatus.NOT_FOUND);
		}
		if (pathRecord.getStartDate() != null){
			return new ResponseEntity<Record>(pathRecord, HttpStatus.NOT_MODIFIED);
		}
		pathRecord.setStartDate(LocalDateTime.now());
		return new ResponseEntity<Record>(recordService.updateRecord(id, pathRecord), HttpStatus.OK);
	}

	@CrossOrigin(methods=RequestMethod.PUT)
	@PreAuthorize("hasAnyAuthority('ROLE_user')")
	@PutMapping("/{id}/end")
	public ResponseEntity<Record> endRecord(@PathVariable("id") Long id, @RequestBody Record record, Authentication authentication){
		Record pathRecord = recordService.findById(id);
		if (pathRecord == null || ! hasAuthority(pathRecord, authentication)){
			return new ResponseEntity<Record> (HttpStatus.NOT_FOUND);
		}
		if (pathRecord.getEndDate() != null){
			return new ResponseEntity<Record>(pathRecord, HttpStatus.NOT_MODIFIED);
		}
		pathRecord.setEndDate(LocalDateTime.now());
		return new ResponseEntity<Record>(recordService.updateRecord(id, pathRecord), HttpStatus.OK);
	}
	
	@CrossOrigin(methods=RequestMethod.DELETE)
	@PreAuthorize("hasAnyAuthority('ROLE_user')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRecord(@PathVariable("id") Long id, Authentication authentication){
		log.info(authentication.getName());
		recordService.deleteRecord(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping
	@PreAuthorize("hasAnyAuthority('ROLE_user')")
	public ResponseEntity<List<Record>> listAllRecord(Authentication authentication) {
		log.info(authentication.getName());
		return new ResponseEntity<List<Record>> (
			filterListByAuthority(recordService.findAllRecord(), authentication), 
			HttpStatus.OK
		);		
	}

	private List<Record> filterListByAuthority(List<Record> response, Authentication authentication){
		if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_admin"))){
			return response;
		} else{
			return response.stream()
				.filter(record -> record.getUser().getUsername().equals(authentication.getName()))
				.collect(Collectors.toList());
		}		
	}
	
	private Boolean hasAuthority(Record record, Authentication authentication){
		if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_admin"))){
			return Boolean.TRUE;
		} else{
			return record.getUser().getUsername().equals(authentication.getName());
		}		
	}

}
