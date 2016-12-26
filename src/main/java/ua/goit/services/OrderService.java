package ua.goit.services;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ua.goit.dao.EmployeeDao;
import ua.goit.dao.OrderDao;
import ua.goit.domain.CookedDish;
import ua.goit.domain.Employee;
import ua.goit.domain.Orders;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderService {
    private OrderDao orderDao;
    private EmployeeDao employeeDao;

    @Transactional
    public Set<Orders> getAllOrders() {
        return orderDao.getAll();
    }

    @Transactional
    public Set<Orders> filterOrdersByDate(Date date) {
        if (date == null) throw new RuntimeException("Cannot use null date");
        return orderDao.filterByDate(date);
    }

    @Transactional
    public Set<Orders> filterOrdersByWaiter(Long employeeId) {
        if (employeeId < 0) throw new RuntimeException("Cannot use null/empty name");
        Employee employee = employeeDao.findById(employeeId);
        return orderDao.filterByWaiter(employee);
    }

    @Transactional
    public Set<Orders> filterByTableNumber(Integer tableNumber) {
        if (tableNumber <= 0) throw new RuntimeException("Table number cannot be zero or negative");
        return orderDao.filterByTableNumber(tableNumber);
    }

    @Transactional
    public void addNewOrder(String waiterName, int tableNumber, Date date, List<CookedDish> dishes) {
        if (StringUtils.isEmpty(waiterName) || tableNumber <= 0 || date == null || dishes == null)
            throw new RuntimeException("Cannot use null values");

        Employee waiter = employeeDao.findByName(waiterName);
        java.sql.Date orderDate = new java.sql.Date(date.getTime());

        Orders order = new Orders(waiter, tableNumber, orderDate, dishes);
        orderDao.add(order);
    }

    @Transactional
    public Set<Integer> getAllTableNumbers() {
        List<Integer> allTableNumbers = orderDao.getAll()
                .stream()
                .map(Orders::getTableNumber)
                .collect(Collectors.toList());

        return new HashSet<>(allTableNumbers);
    }

    @Transactional
    public Orders loadOrderById(long id) {
        return orderDao.loadById(id);
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }
}