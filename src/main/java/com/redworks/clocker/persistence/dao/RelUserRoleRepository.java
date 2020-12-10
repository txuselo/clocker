package com.redworks.clocker.persistence.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redworks.clocker.persistence.entities.RelUserRole;

public interface RelUserRoleRepository extends JpaRepository<RelUserRole, Long> {

    public Optional<RelUserRole> findById(Long id);
    
    
}
