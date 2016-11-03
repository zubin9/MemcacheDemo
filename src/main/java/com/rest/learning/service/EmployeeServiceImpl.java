package com.rest.learning.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.rest.learning.model.Employee;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService{
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<Employee> employees;
	
	static{
		employees = populateDummyEmployees();
	}

	public List<Employee> findAllUsers() {
		return employees;
	}
	
	public Employee findById(long id) {
		for(Employee emp : employees){
			if(emp.getId() == id){
				return emp;
			}
		}
		return null;
	}
	
	private static List<Employee> populateDummyEmployees(){
		List<Employee> users = new ArrayList<Employee>();
		users.add(new Employee(counter.incrementAndGet(),"E123",30, 90000));
		users.add(new Employee(counter.incrementAndGet(),"E345",35, 80000));
		users.add(new Employee(counter.incrementAndGet(),"E567",40, 70000));
		users.add(new Employee(counter.incrementAndGet(),"E789",45, 40000));
		return users;
	}

}
