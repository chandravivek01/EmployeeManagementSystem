package com.g2b1.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.g2b1.ems.dto.RegistrationDto;
import com.g2b1.ems.entity.Role;
import com.g2b1.ems.service.RoleService;
import com.g2b1.ems.service.UserService;

@RequestMapping("/registration")
@Controller
public class RegistrationController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;
	
	@RequestMapping("")
	public String showRegistrationForm(Model model, Model model1) {
		
		model.addAttribute("registrationDto", new RegistrationDto());
		List<Role> roles = roleService.viewAllRoles();
		model1.addAttribute("roles", roles);
		return "registration";
	}
	
	@RequestMapping("/save")
	public String saveUser(@ModelAttribute("user")  RegistrationDto registrationDto) {
		
		userService.save(registrationDto);
		return "redirect:/registration?success";
	}
	
	@RequestMapping("/addrole")
	public String saveRole(@RequestParam("role") String name) {
		
		Role role = new Role(name);
		roleService.save(role);
		
		return "redirect:/registration";
	}
}
