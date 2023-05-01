package com.g2b1.ems.service;

import java.util.List;

import com.g2b1.ems.entity.Role;

public interface RoleService {
	
	void save(Role role);
	
	List<Role> viewAllRoles();

}
