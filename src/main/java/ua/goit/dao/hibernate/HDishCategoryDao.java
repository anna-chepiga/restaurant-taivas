package ua.goit.dao.hibernate;

import org.hibernate.SessionFactory;
import ua.goit.dao.DishCategoryDao;
import ua.goit.domain.DishCategory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HDishCategoryDao implements DishCategoryDao {
    private SessionFactory factory;

    @Override
    public Set<DishCategory> getAll() {
        List<DishCategory> all = factory.getCurrentSession()
                .createQuery("select dc from DishCategory dc", DishCategory.class)
                .list();

        return new HashSet<>(all);
    }

    @Override
    public DishCategory findByName(String name) {
        CriteriaBuilder criteriaBuilder = factory.getCriteriaBuilder();
        CriteriaQuery<DishCategory> query = criteriaBuilder.createQuery(DishCategory.class);
        Root<DishCategory> dishCatRoot = query.from(DishCategory.class);
        query.select(dishCatRoot).where(criteriaBuilder.equal(dishCatRoot.get("name"), name));

        return factory.getCurrentSession().createQuery(query).uniqueResult();
    }

    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }
}
