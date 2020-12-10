package com.redworks.clocker.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="USER")
public class User implements Serializable {
    
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="USERNAME")
	private String username;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="CREATED_AT", insertable=false, updatable=false)
	private LocalDateTime createdAt;
	
	@Column(name="UPDATED_AT", insertable=false, updatable=false)
	private LocalDateTime updatedAt;
	
	@OneToMany(mappedBy="user")
	@JsonIgnoreProperties("user")
	private List<RelUserRole> relUserRole;
	
	public Long getId(){
		return this.id;
	}

	public void setId(Long id){
		this.id = id;
	}
	
	public String getUsername(){
		return this.username;
	}

	public void setUsername(String username){
		this.username = username;
	}
	
	public String getPassword(){
		return this.password;
	}

	public void setPassword(String password){
		this.password = password;
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
	
	public List<RelUserRole> getRelUserRole(){
		return this.relUserRole;
	}

	public void setRelUserRole(List<RelUserRole> relUserRole){
		this.relUserRole = relUserRole;
	}
}