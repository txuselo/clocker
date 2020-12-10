package com.redworks.clocker.persistence.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redworks.clocker.persistence.entities.Record;

public interface RecordRepository extends JpaRepository<Record, Long> {

    public Optional<Record> findById(Long id);
    
    
}
