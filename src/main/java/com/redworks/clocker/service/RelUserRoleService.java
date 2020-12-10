package com.redworks.clocker.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redworks.clocker.persistence.dao.RelUserRoleRepository;
import com.redworks.clocker.persistence.entities.RelUserRole;


@Service
public class RelUserRoleService{

    @Autowired
	private RelUserRoleRepository relUserRoleRepository;
	

    public RelUserRole findById(Long id){
        return relUserRoleRepository.findById(id).get();
    }
	
	public RelUserRole saveRelUserRole(RelUserRole relUserRole){
		return relUserRoleRepository.save(relUserRole);
	}
	
	public RelUserRole updateRelUserRole(Long id, RelUserRole relUserRole){
		if (this.findById(id) != null) {
			relUserRole.setId(id);
			return relUserRoleRepository.save(relUserRole);
		}
		return null;
	}

	public void deleteRelUserRole(Long id){
		RelUserRole relUserRole = new RelUserRole();
		relUserRole.setId(id);
		relUserRoleRepository.delete(relUserRole);
	}

	public List<RelUserRole> findAllRelUserRole(){
		return relUserRoleRepository.findAll();
	}

	public List<RelUserRole> saveListRelUserRole(List<RelUserRole> listRelUserRole) {
		List<RelUserRole> response = new ArrayList<RelUserRole>();
		for (RelUserRole item : listRelUserRole) {
			response.add(this.saveRelUserRole(item));
		}
		return response;
	}

	public List<RelUserRole> updateListRelUserRole(List<RelUserRole> listRelUserRole) {
		List<RelUserRole> response = new ArrayList<RelUserRole>();
		for (RelUserRole item : listRelUserRole) {
			response.add(this.updateRelUserRole(item.getId(), item));
		}
		return response;
	}

	public void deleteListRelUserRole(List<RelUserRole> listRelUserRole) {
		for (RelUserRole item : listRelUserRole) {
			this.deleteRelUserRole(item.getId());
		}
		
	}
	
}
