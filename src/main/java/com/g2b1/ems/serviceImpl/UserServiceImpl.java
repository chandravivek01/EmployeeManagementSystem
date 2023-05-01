package com.g2b1.ems.serviceImpl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.g2b1.ems.dto.RegistrationDto;
import com.g2b1.ems.entity.Role;
import com.g2b1.ems.entity.User;
import com.g2b1.ems.repository.RoleRepository;
import com.g2b1.ems.repository.UserRepository;
import com.g2b1.ems.security.MyUserDetails;
import com.g2b1.ems.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void save(RegistrationDto registrationDto) {
		
		String roleName = registrationDto.getName();
		Role role = roleRepository.findByName(roleName);
		int roleId = role.getId();
		User user1;
		if (roleId!=0) 
			user1 = new User(registrationDto.getUsername(), bCryptPasswordEncoder.encode(registrationDto.getPassword()),
					Arrays.asList(role));
		
		else 
			user1 = new User(registrationDto.getUsername(), bCryptPasswordEncoder.encode(registrationDto.getPassword()),
					Arrays.asList(new Role(registrationDto.getName())));
		
		userRepository.save(user1);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUsername(username);
		
		if(user == null)
			throw new UsernameNotFoundException("Could not find user");
		
		return  new MyUserDetails(user);
	}


	

}
