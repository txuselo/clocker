package com.redworks.clocker.persistence.dao;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redworks.clocker.persistence.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    public Optional<Role> findById(Long id);
    
    
    public List<Role> findByRelUserRoleUserUsername(String username);
}
