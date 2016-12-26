package ua.goit.dao;

import ua.goit.domain.Employee;

import java.util.Set;

public interface EmployeeDao {
    Set<Employee> getAll();

    Employee findByName(String name);

    Employee findById(Long id);

    void add(Employee employee);

    void remove(Employee employee);

    void edit(Employee employee);

    Long getIdOfLastInsertedEmployee();
}