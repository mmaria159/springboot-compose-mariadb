package com.company.controller;

import com.company.model.Employee;
import com.company.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class MyController {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public MyController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @GetMapping("/")
    public String getAllEmployees(Model model) {
        Iterable<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        model.addAttribute("employee", new Employee());
        return "employees";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        Optional<Employee> emp = employeeRepository.findById(id);
        Employee employee = emp.get();
        employeeRepository.delete(employee);
        return "redirect:/";
    }

    @GetMapping("/goToUpdate/{id}")
    public String goToUpdate(@PathVariable("id") int id, Model model) {
        Optional<Employee> emp = employeeRepository.findById(id);
        Employee employee = emp.get();
        model.addAttribute("employee", employee);
        return "updateEmployee";
    }
    @PostMapping("/editEmployee/{id}")
    public String editEmployee(@ModelAttribute("id") int id,
                               @RequestParam String name,
                               @RequestParam String surname,
                               @RequestParam String country,
                               @RequestParam String city,
                               @RequestParam String address) {
        Optional<Employee> emp = employeeRepository.findById(id);
        Employee employee = emp.get();
        employee.setName(name);
        employee.setSurname(surname);
        employee.setCountry(country);
        employee.setCity(city);
        employee.setAddress(address);
        employeeRepository.save(employee);
        return "redirect:/";
    }
}
