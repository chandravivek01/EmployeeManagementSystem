package com.g2b1.ems.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.g2b1.ems.entity.Employee;
import com.g2b1.ems.repository.EmployeeRepository;
import com.g2b1.ems.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public void save(Employee employee) {
		
		employeeRepository.save(employee);
	}

	@Override
	public List<Employee> viewAllEmployees() {
		
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
		
		return employeeRepository.getById(id);
	}

	@Override
	public void removeEmployee(long id) {
	
		employeeRepository.deleteById(id);
	}

	@Override
	public List<Employee> sortEmployeesByFirstname(Direction direction) {
		
		return employeeRepository.findAll(Sort.by(direction, "firstname"));
	}

	
	
}
