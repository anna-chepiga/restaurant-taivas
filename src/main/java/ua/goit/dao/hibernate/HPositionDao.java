package ua.goit.dao.hibernate;

import org.hibernate.SessionFactory;
import ua.goit.dao.PositionDao;
import ua.goit.domain.Position;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HPositionDao implements PositionDao {
    private SessionFactory factory;

    @Override
    public Set<Position> getAll() {
        List<Position> all = factory.getCurrentSession()
                .createQuery("select p from Position p", Position.class)
                .list();

        return new HashSet<>(all);
    }

    @Override
    public Position findByName(String name) {
        return factory.getCurrentSession()
                .createQuery("select p from Position p where p.name like :name", Position.class)
                .setParameter("name", name)
                .uniqueResult();
    }

    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }
}