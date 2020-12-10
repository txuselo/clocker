package com.redworks.clocker.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;


@Entity
@Table(name="REL_USER_ROLE")
@Data
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
	
}