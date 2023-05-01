package com.g2b1.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.g2b1.ems.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

	public Role findByName(String name);
}
