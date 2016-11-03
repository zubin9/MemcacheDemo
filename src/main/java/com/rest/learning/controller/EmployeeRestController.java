package com.rest.learning.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rest.learning.model.Employee;
import com.rest.learning.service.EmployeeService;
 
@RestController
@RequestMapping(value = "/EmployeeController")
public class EmployeeRestController {
 
    @Autowired
    EmployeeService employeeService;
  
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") long id) {
        System.out.println("Fetching Employee with id " + id);
        Employee emp = employeeService.findById(id);
        if (emp == null) {
            System.out.println("Employee with id " + id + " not found");
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Employee>(emp, HttpStatus.OK);
    }
 
}