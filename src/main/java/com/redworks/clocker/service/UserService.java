package com.redworks.clocker.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redworks.clocker.persistence.dao.UserRepository;
import com.redworks.clocker.persistence.entities.User;
import com.redworks.clocker.persistence.dao.RoleRepository;
import com.redworks.clocker.persistence.entities.Role;


@Service
public class UserService implements UserDetailsService{

    @Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
    private RoleRepository roleRepository;

    public User findById(Long id){
        return userRepository.findById(id).get();
    }
	
	public User saveUser(User user){
		user.setPassword((bCryptPasswordEncoder.encode(user.getPassword())));
		return userRepository.save(user);
	}
	
	public User updateUser(Long id, User user){
		if (this.findById(id) != null) {
			user.setId(id);
			user.setPassword((bCryptPasswordEncoder.encode(user.getPassword())));
			return userRepository.save(user);
		}
		return null;
	}

	public void deleteUser(Long id){
		User user = new User();
		user.setId(id);
		userRepository.delete(user);
	}

	public List<User> findAllUser(){
		return userRepository.findAll();
	}

	public List<User> saveListUser(List<User> listUser) {
		List<User> response = new ArrayList<User>();
		for (User item : listUser) {
			response.add(this.saveUser(item));
		}
		return response;
	}

	public List<User> updateListUser(List<User> listUser) {
		List<User> response = new ArrayList<User>();
		for (User item : listUser) {
			response.add(this.updateUser(item.getId(), item));
		}
		return response;
	}

	public void deleteListUser(List<User> listUser) {
		for (User item : listUser) {
			this.deleteUser(item.getId());
		}
		
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		
		if (user != null) {
			List<Role> dbRoles = roleRepository.findByRelUserRoleUserUsername(username);
			
			List<String> roles = new ArrayList<String>();
			for (Role role : dbRoles) {
				roles.add("ROLE_" + role.getRole());
			}
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), 
				AuthorityUtils.createAuthorityList(roles.toArray(new String[0])));
		}else {
			throw new UsernameNotFoundException("User not registered");
		}
	}
}
