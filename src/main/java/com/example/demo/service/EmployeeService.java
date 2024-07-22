package com.example.demo.service;

import com.example.demo.model.Employee;

import java.util.List;

public interface EmployeeService {

    public void addEmployee(Employee employee);

    public List<Employee> getAllEmployees();

    public Employee getEmployee(long id);

    public Employee updateEmployee(Employee employee);

   // public void deleteEmployee(long id);

}
