package com.redworks.clocker.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redworks.clocker.persistence.dao.EmployeeRepository;
import com.redworks.clocker.persistence.entities.Employee;


@Service
public class EmployeeService{

    @Autowired
	private EmployeeRepository employeeRepository;
	

    public Employee findById(Long id){
        return employeeRepository.findById(id).get();
    }
	
	public Employee saveEmployee(Employee employee){
		return employeeRepository.save(employee);
	}
	
	public Employee updateEmployee(Long id, Employee employee){
		if (this.findById(id) != null) {
			employee.setId(id);
			return employeeRepository.save(employee);
		}
		return null;
	}

	public void deleteEmployee(Long id){
		Employee employee = new Employee();
		employee.setId(id);
		employeeRepository.delete(employee);
	}

	public List<Employee> findAllEmployee(){
		return employeeRepository.findAll();
	}

	public List<Employee> saveListEmployee(List<Employee> listEmployee) {
		List<Employee> response = new ArrayList<Employee>();
		for (Employee item : listEmployee) {
			response.add(this.saveEmployee(item));
		}
		return response;
	}

	public List<Employee> updateListEmployee(List<Employee> listEmployee) {
		List<Employee> response = new ArrayList<Employee>();
		for (Employee item : listEmployee) {
			response.add(this.updateEmployee(item.getId(), item));
		}
		return response;
	}

	public void deleteListEmployee(List<Employee> listEmployee) {
		for (Employee item : listEmployee) {
			this.deleteEmployee(item.getId());
		}
		
	}
	
}
