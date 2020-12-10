package com.redworks.clocker.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="REL_USER_ROLE")
public class RelUserRole implements Serializable {
    
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="ID_USER")
	@JsonIgnoreProperties("relUserRole")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="ID_ROLE")
	@JsonIgnoreProperties("relUserRole")
	private Role role;
	
	@Column(name="CREATED_AT", insertable=false, updatable=false)
	private LocalDateTime createdAt;
	
	@Column(name="UPDATED_AT", insertable=false, updatable=false)
	private LocalDateTime updatedAt;
	
	
	public Long getId(){
		return this.id;
	}

	public void setId(Long id){
		this.id = id;
	}
	
	public User getUser(){
		return this.user;
	}

	public void setUser(User user){
		this.user = user;
	}
	
	public Role getRole(){
		return this.role;
	}

	public void setRole(Role role){
		this.role = role;
	}
	
	public LocalDateTime getCreatedAt(){
		return this.createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt){
		this.createdAt = createdAt;
	}
	
	public LocalDateTime getUpdatedAt(){
		return this.updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt){
		this.updatedAt = updatedAt;
	}
	
	
}