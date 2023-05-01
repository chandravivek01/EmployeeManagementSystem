package com.g2b1.ems.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.g2b1.ems.entity.Employee;
import com.g2b1.ems.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;

	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/employee")
	// public String home(Model model, Model model1, Principal user) {
	public String home(Model model) {
		
		//System.out.println(user.getName());
		//model1.addAttribute("user", user);
		List<Employee> employees = employeeService.viewAllEmployees();
		model.addAttribute("employees", employees);
		return "home";	
	}
	
	@RequestMapping("/employee/addemployee")
	public String employeeForm(Model model) {
		
		model.addAttribute("employee", new Employee());
		return "employee-form";
	}
	
	@RequestMapping("/employee/save")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		
		employeeService.save(employee);
		return "redirect:/employee";
	}
	
	@RequestMapping("/employee/update")
	public String updateEmployeeForm(@RequestParam("employeeId") long id, Model model) {
		
		model.addAttribute("employee", employeeService.findEmployeeById(id));
		return "update-employee-form";
	}
	
	@RequestMapping("/employee/delete")
	public String removeEmployee(@RequestParam("employeeId") long id) {
		
		employeeService.removeEmployee(id);
		return "redirect:/employee";
	}
	
	@RequestMapping(value = "/403")
	public String accesssDenied(Principal user, Model model) {
	
		model.addAttribute("user", user);
		return "403";
		
	}
	
 }
