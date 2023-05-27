package service;


import exception.EmployeeAlreadyAddedException;
import exception.EmployeeNotFoundException;
import exception.EmployeeStorageIsFullException;
import model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

@Service

public class EmployeeService {
    private final Map<String, Employee> employees = new HashMap<>();
    private static final int MAX_SIZE = 5;
    private Employee employee;

    public Employee add(String firstName, String lastName) {
        if (employees.size()>= MAX_SIZE){
            throw new EmployeeStorageIsFullException();
        }
        Employee employeeToAdd = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFullName())){
            throw new EmployeeAlreadyAddedException();
        }

        employees.put(employee.getFullName(),employee);
        return employee;
    }

    public Employee remove(String firstName, String lastName){
        Employee employeeToRemove = new Employee(firstName, lastName);
        if (!employees.containsKey(employee.getFullName())){
            throw new EmployeeNotFoundException();
        }
        employees.remove(employee.getFullName());
        return employeeToRemove;
    }

    public Employee find(String firstName, String lastName){
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFullName())) {
            return employees.get(employee.getFullName());
            }
        throw new EmployeeNotFoundException();
    }

    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employees.values());
    }
}




