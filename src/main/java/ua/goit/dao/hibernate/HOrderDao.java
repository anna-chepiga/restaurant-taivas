package ua.goit.dao.hibernate;

import org.hibernate.SessionFactory;
import ua.goit.dao.OrderDao;
import ua.goit.domain.Employee;
import ua.goit.domain.Orders;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HOrderDao implements OrderDao {
    private SessionFactory factory;

    @Override
    public Set<Orders> getAll() {
        List<Orders> all = factory.getCurrentSession()
                .createQuery("select o from Orders o", Orders.class)
                .list();

        return new HashSet<>(all);
    }

    @Override
    public Set<Orders> filterByDate(Date date) {
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        List<Orders> orders = factory.getCurrentSession()
                .createQuery("select o from Orders o where o.orderDate like :date", Orders.class)
                .setParameter("date", sqlDate)
                .getResultList();

        return new HashSet<>(orders);
    }

    @Override
    public Set<Orders> filterByWaiter(Employee employee) {
        List<Orders> orders = factory.getCurrentSession()
                .createQuery("select o from Orders o where o.waiter like :waiter", Orders.class)
                .setParameter("waiter", employee)
                .getResultList();

        return new HashSet<>(orders);
    }

    @Override
    public Set<Orders> filterByTableNumber(int tableNumber) {
        List<Orders> orders = factory.getCurrentSession()
                .createQuery("select o from Orders o where o.tableNumber like :tableNumber", Orders.class)
                .setParameter("tableNumber", tableNumber)
                .getResultList();

        return new HashSet<>(orders);
    }

    @Override
    public void add(Orders order) {
        factory.getCurrentSession().save(order);
    }

    @Override
    public Orders loadById(long id) {
        return factory.getCurrentSession().load(Orders.class, id);
    }

    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }
}