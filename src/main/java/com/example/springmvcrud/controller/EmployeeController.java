package com.example.springmvcrud.controller;

import com.example.springmvcrud.domain.EmployeeDTO;
import com.example.springmvcrud.entity.Employee;
import com.example.springmvcrud.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
@RequestMapping("welcome")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service){
        this.service=service;
    }

    @RequestMapping("home")
    public String welcomePage(){
        return "home";
    }

    @RequestMapping("/addEmployee")
    public String addEmployee(Model model){
        model.addAttribute("command", new EmployeeDTO());
        return "addEmployee";
    }

    @RequestMapping(value="/save",method = RequestMethod.POST)
    public String save(@ModelAttribute("employee") EmployeeDTO employee){
        service.saveEmployee(employee);
        return "redirect:/welcome/home";//will redirect to home request mapping
    }

    @RequestMapping(value="/viewEmployee",method = RequestMethod.GET)
    public String viewEmployee(Model model){
        model.addAttribute("list",service.findAllEmployees());
        return "viewEmployee";//will redirect to home request mapping
    }

    @RequestMapping(value="/editemp/{id}")
    public String edit(@PathVariable Long id, Model m){
        Optional<Employee> optionalEmployee = service.getEmployeeById(id);
        Employee employee= optionalEmployee.get();
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setDepartment(employee.getDepartment());
        employeeDTO.setName(employee.getName());
        employeeDTO.setSalary(employee.getSalary());
        m.addAttribute("command",employeeDTO);
        return "editEmployee";
    }

    @RequestMapping(value="/editSave",method = RequestMethod.POST)
    public String editSave(@ModelAttribute("employee") EmployeeDTO employeeDTO){
        System.out.println(employeeDTO);
        service.saveEmployee(employeeDTO);
        return "redirect:/welcome/viewEmployee";
    }

    /* It deletes record for the given id in URL and redirects to /viewemp */
    @RequestMapping(value="/deleteemp/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable Long id){
        service.deleteEmployeeById(id);
        return "redirect:/welcome/viewEmployee";
    }

}
