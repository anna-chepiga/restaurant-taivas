package ua.goit.dao.hibernate;

import org.hibernate.SessionFactory;
import ua.goit.dao.DishDao;
import ua.goit.domain.Dish;
import ua.goit.domain.DishCategory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HDishDao implements DishDao {
    private SessionFactory factory;
    private HDishCategoryDao dishCategoryDao;

    @Override
    public Set<Dish> getAll() {
        List<Dish> all = factory.getCurrentSession()
                .createQuery("select d from Dish d", Dish.class)
                .list();
        factory.getCurrentSession().clear();
        return new HashSet<>(all);
    }

    @Override
    public Dish findByName(String name) {
        return factory.getCurrentSession()
                .createQuery("select d from Dish d where d.dishName like :name", Dish.class)
                .setParameter("name", name)
                .uniqueResult();
    }

    @Override
    public Dish findById(Long id) {
        return factory.getCurrentSession().get(Dish.class, id);
    }

    @Override
    public List<Dish> findByCategory(DishCategory category) {
        return factory.getCurrentSession()
                .createQuery("select d from Dish d where d.category like :category", Dish.class)
                .setParameter("category", category)
                .list();
    }

    @Override
    public void add(Dish dish) {
        String categoryName = dish.getCategory().getName();
        DishCategory category = dishCategoryDao.findByName(categoryName);
        dish.setCategory(category);

        factory.getCurrentSession().save(dish);
    }

    @Override
    public void remove(Dish dish) {
        factory.getCurrentSession()
                .createQuery("delete from Dish d where d.id = :id")
                .setParameter("id", dish.getId())
                .executeUpdate();
    }

    @Override
    public void edit(Dish dish) {
        factory.getCurrentSession().update(dish);
    }

    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    public void setDishCategoryDao(HDishCategoryDao dishCategoryDao) {
        this.dishCategoryDao = dishCategoryDao;
    }
}