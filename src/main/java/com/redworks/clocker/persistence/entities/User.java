package com.redworks.clocker.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;


@Entity
@Table(name="USER")
@Data
public class User implements Serializable {
    
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="USERNAME", unique = true)
	private String username;
	
	@Column(name="PASSWORD")
	private String password;

	@Column(name="NAME")
	private String name;

	@Column(name="SURNAME")
	private String surname;
	
	@Column(name="CREATED_AT", insertable=false, updatable=false)
	private LocalDateTime createdAt;
	
	@Column(name="UPDATED_AT", insertable=false, updatable=false)
	private LocalDateTime updatedAt;
	
	@OneToMany(mappedBy="user")
	@JsonIgnoreProperties("user")
	private List<RelUserRole> relUserRole;

}