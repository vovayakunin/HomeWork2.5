package service;


import exception.EmployeeAlreadyAddedException;
import exception.EmployeeNotFoundException;
import exception.EmployeeStorageIsFullException;
import model.Employee;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service

public class EmployeeService {
    private static final int MAX_SIZE = 5;
    private final Map<String, Employee> employees = new HashMap<>(MAX_SIZE);

    public EmployeeService() {
    }


    public Collection<Employee> getAll() {
        return employees.values();
    }

    public Employee add(Employee employee)  {
        if (employees.size() >= MAX_SIZE) {
            throw new EmployeeStorageIsFullException();
        }
        if (employees.containsKey(createKey(employee))) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(createKey(employee), employee);
        return employee;
    }

    public Employee find(String firstName, String lastName) {
        Employee employee = employees.get(createKey(firstName, lastName));
        if (employee == null) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    public Employee remove(String firstName, String lastName) {
        return employees.remove(createKey(firstName, lastName));
    }

    private static String createKey(Employee employee) {
        return createKey(employee.getFirstName(), employee.getLastName());
    }

    private static String createKey(String firstName, String lastName) {
        return (firstName + lastName).toLowerCase();
    }

    private static void correctCase(Employee employee) {
        employee.setFirstName(StringUtils.capitalize((employee.getFirstName().toLowerCase())));
        employee.setLastName(StringUtils.capitalize((employee.getLastName().toLowerCase())));
    }

    public void add() {
    }
}


