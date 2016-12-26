package ua.goit.dao.hibernate;

import org.hibernate.SessionFactory;
import ua.goit.dao.UserRoleDao;
import ua.goit.domain.UserRole;

public class HUserRoleDao implements UserRoleDao {
    private SessionFactory factory;

    @Override
    public UserRole findByName(String roleName) {
        return factory.getCurrentSession()
                .createQuery("select ur from UserRole ur where ur.name like :name", UserRole.class)
                .setParameter("name", roleName)
                .uniqueResult();
    }



    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }
}