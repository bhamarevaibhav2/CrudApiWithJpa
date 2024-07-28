package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("EmployeeInfo")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmployeeService employeeService;

	// get all employees
	@GetMapping("/getAllEmployees")
	public List<Employee> getAllEmployees(){

		return employeeService.getAllEmployees();
	}		

	// add employee rest api
	@PostMapping("/addEmployee")
	public ResponseEntity<Employee> AddEmployee(@RequestBody Employee employee) {
		employeeService.addEmployee(employee);
		return ResponseEntity.ok(employee);
	}

	// get employee by id rest api
	@GetMapping("/getEmployeeById/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
		return ResponseEntity.ok(employee);
	}

	// update employee rest api
	@PutMapping("/updateEmployee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));

		employee.setEmpName(employeeDetails.getEmpName());
		employee.setEmail(employeeDetails.getEmail());
		employee.setPhone(employeeDetails.getPhone());
		employee.setCity((employeeDetails.getCity()));
		employee.setAge(employeeDetails.getAge());
		employee.setDepartment(employeeDetails.getDepartment());
		employee.setJobTitle(employeeDetails.getJobTitle());

		Employee updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}

	// delete employee rest api
	@DeleteMapping("/deleteEmployee/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));

		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	//All customer finder methods repo
	@GetMapping("/getEmployeeByNameStartWith/{prefix}")
	public List<Employee> getEmployeesByNameStartWith(@PathVariable String prefix){
		return employeeRepository.findByEmpNameStartingWith(prefix);
	}

	//All @Query methods
	//JPQL
	@GetMapping("/getAllEmployeecust")
	public List<Employee> getEmployeesCust(){
		return employeeRepository.findAllEmployees();
	}
	@GetMapping("/getEmployeeByName/{empName}")
	public List<Employee> employeeByName(@PathVariable String empName){
		return employeeRepository.findByName(empName);
	}

	//NATIVE SQL
	@GetMapping("/getData")
	public List<Employee> getData(){
		return employeeRepository.findAllByNativeQuery();
	}
	@GetMapping("/getOneData/{empId}")
	public Employee getOneData(@PathVariable long empId){
		return employeeRepository.findByEmpId(empId);
	}

}
