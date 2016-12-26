package ua.goit.dao.hibernate;

import org.hibernate.SessionFactory;
import ua.goit.dao.IngredientDao;
import ua.goit.domain.Ingredient;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HIngredientDao implements IngredientDao {
    private SessionFactory factory;

    @Override
    public Set<Ingredient> getAll() {
        List<Ingredient> all = factory.getCurrentSession()
                .createQuery("select i from Ingredient i", Ingredient.class)
                .list();

        return new HashSet<>(all);
    }

    @Override
    public Ingredient findByName(String name) {
        return factory.getCurrentSession()
                .createQuery("select i from Ingredient i where i.name like :name", Ingredient.class)
                .setParameter("name", name)
                .uniqueResult();
    }

    @Override
    public void add(Ingredient ingredient) {
        factory.getCurrentSession().save(ingredient);
    }

    @Override
    public void remove(Ingredient ingredient) {
        factory.getCurrentSession()
                .createQuery("delete from Ingredient i where i.id = :id")
                .setParameter("id", ingredient.getId())
                .executeUpdate();
    }

    @Override
    public void edit(Ingredient ingredient) {
        factory.getCurrentSession().update(ingredient);
    }

    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }
}