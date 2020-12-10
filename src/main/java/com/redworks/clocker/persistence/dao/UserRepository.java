package com.redworks.clocker.persistence.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redworks.clocker.persistence.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findById(Long id);
    
    public User findByUsername(String username);
    
}
