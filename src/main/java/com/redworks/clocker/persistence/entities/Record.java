package com.redworks.clocker.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;


@Entity
@Table(name="RECORD")
@Data
public class Record implements Serializable {
    
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="CREATED_AT", insertable=false, updatable=false)
	private LocalDateTime createdAt;
	
	@Column(name="UPDATED_AT", insertable=false, updatable=false)
	private LocalDateTime updatedAt;
	
	@Column(name="START_DATE")
	private LocalDateTime startDate;
	
	@Column(name="END_DATE")
	private LocalDateTime endDate;
	
	@ManyToOne
	@JoinColumn(name="ID_USER")
	@JsonIgnoreProperties("record")
	private User user;	
	
}