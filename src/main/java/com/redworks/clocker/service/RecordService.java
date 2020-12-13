package com.redworks.clocker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.redworks.clocker.persistence.dao.RecordRepository;
import com.redworks.clocker.persistence.entities.Record;


@Service
public class RecordService{

    @Autowired
	private RecordRepository recordRepository;
	
	@Nullable
    public Record findById(Long id){
		if (recordRepository.findById(id).isPresent()){
			return recordRepository.findById(id).get();
		}else{
			return null;
		}
        
    }
	
	public Record saveRecord(Record record){
		return recordRepository.save(record);
	}
	
	public Record updateRecord(Long id, Record record){
		if (this.findById(id) != null) {
			record.setId(id);
			return recordRepository.save(record);
		}
		return null;
	}

	public void deleteRecord(Long id){
		Record record = new Record();
		record.setId(id);
		recordRepository.delete(record);
	}

	public List<Record> findAllRecord(){
		return recordRepository.findAll();
	}

	public List<Record> saveListRecord(List<Record> listRecord) {
		List<Record> response = new ArrayList<Record>();
		for (Record item : listRecord) {
			response.add(this.saveRecord(item));
		}
		return response;
	}

	public List<Record> updateListRecord(List<Record> listRecord) {
		List<Record> response = new ArrayList<Record>();
		for (Record item : listRecord) {
			response.add(this.updateRecord(item.getId(), item));
		}
		return response;
	}

	public void deleteListRecord(List<Record> listRecord) {
		for (Record item : listRecord) {
			this.deleteRecord(item.getId());
		}
		
	}
	
	public List<Record> filterByUsername(String username){
		return recordRepository.findAll().stream()
			.filter(record -> record.getUser().getUsername().equals(username))
			.collect(Collectors.toList());
	}

	public Record partialUpdate(Record origin, Record changes){
		if (changes.getStartDate() != null){
			origin.setStartDate(changes.getStartDate());
		}
		if (changes.getEndDate() != null){
			origin.setEndDate(changes.getEndDate());
		}
		return origin;
	}

}
