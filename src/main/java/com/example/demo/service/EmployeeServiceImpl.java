package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepo;


    public void addEmployee(Employee employee) {

        employeeRepo.save(employee);
    }

    public List<Employee> getAllEmployees() {

        return employeeRepo.findAll();
    }

    public Employee getEmployee(long empId) {

        return employeeRepo.getOne(empId);
    }

    public Employee updateEmployee(Employee employee) {
        employeeRepo.save(employee);
        return employee;
    }

//    public void deleteEmployee(long id) {
//
//        employeeRepo.deleteById(id);
//    }
}
