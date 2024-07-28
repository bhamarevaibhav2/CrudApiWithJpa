package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Employee;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

    //custom finder methods
    public Employee findByEmpName(String name);
    public Employee findByEmpNameAndAge(String name, int age);
    public List<Employee> findByEmpNameStartingWith(String prefix);
    public Employee findByEmpNameEndingWith(String suffix);
    public Employee findByEmpNameContaining(String prefix);
    public Employee findByEmpNameLike(String nameLike);
    public Employee findByAgeLessThan(Integer age);

    //use of @Query annotation
    //JPQL
    @Query("select e from Employee e")
    public List<Employee> findAllEmployees();

    @Query("select e from Employee e where e.empName = :n") // e.empId = :i and
    public List<Employee> findByName(@Param("n") String name); //, @Param("i") long id

    //Native sql
    @Query(value = "SELECT * FROM Employee", nativeQuery = true)
    public List<Employee> findAllByNativeQuery();

    @Query(value = "Select * from Employee where Employee.emp_Id = :empId", nativeQuery = true)
    public Employee findByEmpId(Long empId);
}
