package com.g2b1.ems.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.g2b1.ems.dto.RegistrationDto;

public interface UserService extends UserDetailsService{
	
	void save(RegistrationDto registrationDto);
	
	
}
