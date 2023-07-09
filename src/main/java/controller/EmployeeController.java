package controller;

import model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")

public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    @GetMapping("/add")
    public Employee add(String firstName, String lastName,int department, double salary) throws InterruptedException {
        return employeeService.add(new Employee(firstName, lastName, 1, 1));
    }
    @GetMapping("/remove")
    public Employee remove(String firstName, String lastName) {
        return employeeService.remove(firstName, lastName);
    }
    @GetMapping("/find")
    public Employee find(String firstName,  String lastName) {
        return employeeService.find(firstName, lastName);
    }
    @GetMapping("/all")
    public List<Employee> all(){
        return (List<Employee>) employeeService.getAll();
    }
}
