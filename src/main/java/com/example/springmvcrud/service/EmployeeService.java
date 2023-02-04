package com.example.springmvcrud.service;

import com.example.springmvcrud.domain.EmployeeDTO;
import com.example.springmvcrud.entity.Employee;
import com.example.springmvcrud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public void saveEmployee(EmployeeDTO emp){
        Employee employee = new Employee();
        employee.setDepartment(emp.getDepartment());
        employee.setName(emp.getName());
        employee.setSalary(emp.getSalary());
        repository.save(employee);
    }

    public Optional<Employee> getEmployeeById(Long id){
        return repository.findById(id);
    }
    public void deleteEmployeeById(Long id){
         repository.deleteById(id);
    }

    public List<Employee> findAllEmployees(){
        return repository.findAll();
    }

}
