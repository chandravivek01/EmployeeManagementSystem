package com.g2b1.ems.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g2b1.ems.entity.Role;
import com.g2b1.ems.repository.RoleRepository;
import com.g2b1.ems.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public void save(Role role) {
		
		roleRepository.save(role);
	}

	@Override
	public List<Role> viewAllRoles() {
		
		return roleRepository.findAll();
	}

}
