package com.redworks.clocker.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="ROLE")
public class Role implements Serializable {
    
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="ROLE")
	private String role;
	
	@Column(name="CREATED_AT", insertable=false, updatable=false)
	private LocalDateTime createdAt;
	
	@Column(name="UPDATED_AT", insertable=false, updatable=false)
	private LocalDateTime updatedAt;
	
	@OneToMany(mappedBy="role")
	@JsonIgnoreProperties("role")
	private List<RelUserRole> relUserRole;
	
	public Long getId(){
		return this.id;
	}

	public void setId(Long id){
		this.id = id;
	}
	
	public String getRole(){
		return this.role;
	}

	public void setRole(String role){
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
	
	public List<RelUserRole> getRelUserRole(){
		return this.relUserRole;
	}

	public void setRelUserRole(List<RelUserRole> relUserRole){
		this.relUserRole = relUserRole;
	}
}