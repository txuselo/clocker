package com.redworks.clocker.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redworks.clocker.persistence.dao.RecordRepository;
import com.redworks.clocker.persistence.entities.Record;


@Service
public class RecordService{

    @Autowired
	private RecordRepository recordRepository;
	

    public Record findById(Long id){
        return recordRepository.findById(id).get();
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
	
}
