package com.redworks.clocker.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;


@Entity
@Table(name="ROLE")
@Data
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
	
}