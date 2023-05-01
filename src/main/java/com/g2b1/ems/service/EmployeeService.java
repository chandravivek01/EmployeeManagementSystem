package com.g2b1.ems.service;

import java.util.List;

import com.g2b1.ems.entity.Employee;

public interface EmployeeService {
		
	public void save(Employee employee);
	
	public List<Employee> viewAllEmployees();
	
	public Employee findEmployeeById(long id);
	
	public void removeEmployee(long id);
}
