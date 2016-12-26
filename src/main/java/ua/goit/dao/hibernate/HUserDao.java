package ua.goit.dao.hibernate;

import org.hibernate.SessionFactory;
import ua.goit.dao.UserDao;
import ua.goit.domain.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HUserDao implements UserDao {
    private SessionFactory factory;

    @Override
    public Set<User> getAll() {
        List<User> all = factory.getCurrentSession()
                .createQuery("select u from User u", User.class)
                .list();

        return new HashSet<>(all);
    }

    @Override
    public User findByUsername(String username) {
        return factory.getCurrentSession()
                .createQuery("select u from User u where u.username like :name", User.class)
                .setParameter("name", username)
                .uniqueResult();
    }

    @Override
    public void add(User user) {
        factory.getCurrentSession().save(user);
    }

    @Override
    public void edit(User user) {
        factory.getCurrentSession().update(user);
    }

    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }
}