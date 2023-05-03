package com.g2b1.ems.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.g2b1.ems.entity.Employee;
import com.g2b1.ems.repository.EmployeeRepository;
import com.g2b1.ems.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmployeeRepository employeeRepository;

	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/employee")
	public String home(Model model, Model model1) {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username="";
		if (principal instanceof UserDetails) 
			username = ((UserDetails)principal).getUsername();
		else 
			username = principal.toString();
		model1.addAttribute("user", username);
		
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
		
		model.addAttribute("employee", employeeService.getEmployeeById(id));
		return "update-employee-form";
	}
	
	@RequestMapping("/employee/delete")
	public String removeEmployee(@RequestParam("employeeId") long id) {
		
		employeeService.removeEmployee(id);
		return "redirect:/employee";
	}
	
	@RequestMapping("/employee/view")
	public String showAnEmployee(@RequestParam("employeeId") long id, Model model) {
		
		model.addAttribute("employee", employeeService.getEmployeeById(id));
		return "view-employee";
	}
	
	@GetMapping("/employee/search")
	public String searchTickets(@RequestParam("query") String query, Model model) {
		
		if (query.trim().isEmpty())
			return "redirect:/employee";
			
		List<Employee> searchedEmployees = employeeRepository.findEmployeeByFirstname(query);
		
		model.addAttribute("employees", searchedEmployees);
		return "home";
	}
	
	@RequestMapping(value = "/403")
	public String accesssDenied(Principal user, Model model) {
	
		model.addAttribute("user", user);
		return "403";
		
	}
	@RequestMapping("/employee/sort")
	public String sortByFirstname(@RequestParam("query") String query, Model model) {
		
		List<Employee> sortedEmployees;
		if (query.trim().equals("ASC")) 
			sortedEmployees = employeeService.sortEmployeesByFirstname(Direction.ASC);
		else
			sortedEmployees = employeeService.sortEmployeesByFirstname(Direction.DESC);
		
		model.addAttribute("employees", sortedEmployees);
		return "home";
	}
	
 }
