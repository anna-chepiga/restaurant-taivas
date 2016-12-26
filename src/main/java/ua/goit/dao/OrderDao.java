package ua.goit.dao;

import ua.goit.domain.Employee;
import ua.goit.domain.Orders;

import java.util.Date;
import java.util.Set;

public interface OrderDao {
    Set<Orders> getAll();

    Set<Orders> filterByDate(Date date);

    Set<Orders> filterByWaiter(Employee employee);

    Set<Orders> filterByTableNumber(int tableNumber);

    void add(Orders order);

    Orders loadById(long id);
}