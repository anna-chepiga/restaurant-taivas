package ua.goit.dao.hibernate;

import org.hibernate.SessionFactory;
import ua.goit.dao.MenuDao;
import ua.goit.domain.Menu;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HMenuDao implements MenuDao {
    private SessionFactory factory;

    @Override
    @SuppressWarnings("unchecked")
    public Set<Menu> getAll() {
        List<Menu> all = factory.getCurrentSession()
                .getNamedQuery("Menu.findAll")
                .list();

        factory.getCurrentSession().clear();
        return new HashSet<>(all);
    }

    @Override
    public Menu findByName(String name) {
        return (Menu) factory.getCurrentSession()
                .getNamedQuery("Menu.findByName")
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public void add(Menu menu) {
        factory.getCurrentSession().save(menu);
    }

    @Override
    public void remove(Menu menu) {
        factory.getCurrentSession()
                .getNamedQuery("Menu.deleteById")
                .setParameter("id", menu.getId())
                .executeUpdate();
    }

    @Override
    public void edit(Menu menu) {
        factory.getCurrentSession().update(menu);
    }

    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }
}