package com.redworks.clocker.persistence.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redworks.clocker.persistence.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    public Optional<Employee> findById(Long id);
    
    
}
