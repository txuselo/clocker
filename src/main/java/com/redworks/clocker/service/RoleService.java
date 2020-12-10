package com.redworks.clocker.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redworks.clocker.persistence.dao.RoleRepository;
import com.redworks.clocker.persistence.entities.Role;


@Service
public class RoleService{

    @Autowired
	private RoleRepository roleRepository;
	

    public Role findById(Long id){
        return roleRepository.findById(id).get();
    }
	
	public Role saveRole(Role role){
		return roleRepository.save(role);
	}
	
	public Role updateRole(Long id, Role role){
		if (this.findById(id) != null) {
			role.setId(id);
			return roleRepository.save(role);
		}
		return null;
	}

	public void deleteRole(Long id){
		Role role = new Role();
		role.setId(id);
		roleRepository.delete(role);
	}

	public List<Role> findAllRole(){
		return roleRepository.findAll();
	}

	public List<Role> saveListRole(List<Role> listRole) {
		List<Role> response = new ArrayList<Role>();
		for (Role item : listRole) {
			response.add(this.saveRole(item));
		}
		return response;
	}

	public List<Role> updateListRole(List<Role> listRole) {
		List<Role> response = new ArrayList<Role>();
		for (Role item : listRole) {
			response.add(this.updateRole(item.getId(), item));
		}
		return response;
	}

	public void deleteListRole(List<Role> listRole) {
		for (Role item : listRole) {
			this.deleteRole(item.getId());
		}
		
	}
	
}
