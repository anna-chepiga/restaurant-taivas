package ua.goit.dao.hibernate;

import org.hibernate.SessionFactory;
import ua.goit.dao.RestaurantInfoDao;
import ua.goit.domain.RestaurantInfo;

public class HRestaurantInfoDao implements RestaurantInfoDao {
    private SessionFactory factory;

    @Override
    public RestaurantInfo getAllDetails() {
        return factory.getCurrentSession()
                .createQuery("select ri from RestaurantInfo ri", RestaurantInfo.class)
                .uniqueResult();
    }

    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }
}
