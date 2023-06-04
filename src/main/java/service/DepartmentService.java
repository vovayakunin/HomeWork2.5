package service;

import exception.EmployeeNotFoundException;
import model.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;
    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    public Employee getEmployeeWithMaxSalary(int department) {
       return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartament() == department)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);

    }
    public Employee getEmployeeWithMinSalary(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartament() == department)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);

    }
    public List<Employee> getAll(int department) {
       return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartament()==department)
                .collect(Collectors.toList());
    }
    public Map<Integer,List<Employee>> getAll(){
       return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartament));

    }

}
