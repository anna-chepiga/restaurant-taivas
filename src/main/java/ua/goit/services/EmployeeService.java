package ua.goit.services;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ua.goit.dao.EmployeeDao;
import ua.goit.dao.PositionDao;
import ua.goit.domain.Cook;
import ua.goit.domain.Employee;
import ua.goit.domain.Position;
import ua.goit.domain.Waiter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EmployeeService {
    private EmployeeDao employeeDao;
    private PositionDao positionDao;

    @Transactional
    public Set<Employee> findAll() {
        return employeeDao.getAll();
    }

    @Transactional
    public Employee getEmployeeByName(String employeeName) {
        if (StringUtils.isEmpty(employeeName)) throw new RuntimeException("Cannot use this name: " + employeeName);

        return employeeDao.findByName(employeeName);
    }

    @Transactional
    public Employee getEmployeeById(Long id) {
        if (id <= 0) throw new RuntimeException("Id cannot be negative: " + id);

        return employeeDao.findById(id);
    }

    @Transactional
    public void removeEmployee(Long employeeId) {
        if (employeeId <= 0) throw new RuntimeException("Cannot use negative employee id");
        Employee employee = employeeDao.findById(employeeId);
        employeeDao.remove(employee);
    }

    @Transactional
    public void addNewEmployee(String name, String lastName, String positionName, Integer salary) {
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(lastName) ||
                StringUtils.isEmpty(positionName) || salary <=0) {
            throw new RuntimeException("First name, last name and position name cannot be null/empty, salary cannot be negative or zero." +
                    "Please go back and check the entered details");
        }

        Position position = positionDao.findByName(positionName);
        Employee employee;

        switch (positionName) {
            case "Waiter":
                employee = new Waiter(name, lastName, position, salary);
                break;
            case "Cook":
                employee = new Cook(name, lastName, position, salary);
                break;
            default:
                employee = new Employee(name, lastName, position, salary);
                break;
        }

        Set<Employee> allEmployees = new HashSet<>(findAll());
        if (!allEmployees.contains(employee)) {
            employeeDao.add(employee);
        } else throw new RuntimeException("Employee already exists");
    }

    @Transactional
    public void editEmployee(Employee employee) {
        if (employee == null) throw new RuntimeException("Employee cannot be null");

        employeeDao.edit(employee);
    }

    @Transactional
    public List<Employee> getAllWaiters() {
        List<Employee> waiters = new ArrayList<>();
        waiters.addAll(employeeDao.getAll()
                .stream()
                .filter(employee -> employee.getPosition().getName().equals("Waiter"))
                .collect(Collectors.toList()));

        return waiters;
    }

    @Transactional
    public Long getIdOfLastInsertedEmployee() {
        return employeeDao.getIdOfLastInsertedEmployee();
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }
}