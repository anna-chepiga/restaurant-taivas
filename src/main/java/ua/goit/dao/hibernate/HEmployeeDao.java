package ua.goit.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import ua.goit.dao.EmployeeDao;
import ua.goit.dao.PositionDao;
import ua.goit.domain.Employee;
import ua.goit.domain.Position;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HEmployeeDao implements EmployeeDao {
    private SessionFactory factory;
    private PositionDao positionDao;

    @Override
    public Set<Employee> getAll() {
        List<Employee> all = factory.getCurrentSession()
                .createQuery("select e from Employee e", Employee.class)
                .list();

        return new HashSet<>(all);
    }

    @Override
    public Employee findByName(String name) {
        return factory.getCurrentSession()
                .createQuery("select e from Employee e where e.firstName like :name", Employee.class)
                .setParameter("name", name)
                .uniqueResult();
    }

    @Override
    public Employee findById(Long id) {
        return factory.getCurrentSession().get(Employee.class, id);
    }

    @Override
    public void remove(Employee employee) {
        factory.getCurrentSession()
                .createQuery("delete from Employee e where e.id = :id", Employee.class)
                .setParameter("id", employee.getId())
                .executeUpdate();

        //factory.getCurrentSession().delete(employee);
    }

    @Override
    public void add(Employee employee) {
        String positionName = employee.getPosition().getName();
        Position position = positionDao.findByName(positionName);
        employee.setPosition(position);
        factory.getCurrentSession().save(employee);
    }

    @Override
    public void edit(Employee employee) {
        factory.getCurrentSession().update(employee);
    }

    @Override
    public Long getIdOfLastInsertedEmployee() {
        StoredProcedureQuery query = factory.getCurrentSession()
                .createStoredProcedureQuery("last_inserted_id")
                .registerStoredProcedureParameter("lastId", Long.class, ParameterMode.OUT);

        query.execute();
        return (Long) query.getOutputParameterValue("lastId");
    }

    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }
}