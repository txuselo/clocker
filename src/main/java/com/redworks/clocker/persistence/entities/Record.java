package com.redworks.clocker.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="RECORD")
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
	@JoinColumn(name="ID_EMPLOYEE")
	@JsonIgnoreProperties("record")
	private Employee employee;
	
	
	public Long getId(){
		return this.id;
	}

	public void setId(Long id){
		this.id = id;
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
	
	public LocalDateTime getStartDate(){
		return this.startDate;
	}

	public void setStartDate(LocalDateTime startDate){
		this.startDate = startDate;
	}
	
	public LocalDateTime getEndDate(){
		return this.endDate;
	}

	public void setEndDate(LocalDateTime endDate){
		this.endDate = endDate;
	}
	
	public Employee getEmployee(){
		return this.employee;
	}

	public void setEmployee(Employee employee){
		this.employee = employee;
	}
	
	
}