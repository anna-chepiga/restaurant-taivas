package ua.goit.dao.hibernate;

import org.hibernate.SessionFactory;
import ua.goit.dao.CookedDishDao;
import ua.goit.dao.IngredientDao;
import ua.goit.domain.CookedDish;
import ua.goit.domain.Ingredient;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HCookedDishDao implements CookedDishDao {
    private SessionFactory factory;
    private IngredientDao ingredientDao;

    @Override
    public Set<CookedDish> getAll() {
        List<CookedDish> all = factory.getCurrentSession()
                .createQuery("select ck from CookedDish ck", CookedDish.class)
                .list();

        return new HashSet<>(all);
    }

    @Override
    public void add(CookedDish cookedDish) {
        List<Ingredient> ingredients = cookedDish.getDishDescription().getIngredients();
        final boolean[] enoughIngredients = {true};

        ingredients.stream()
                .filter(i -> i.getAmount() < 1)
                .forEach(i -> enoughIngredients[0] = false);

        if (enoughIngredients[0]) {
            factory.getCurrentSession().save(cookedDish);
        } else
            throw new RuntimeException("Not enough ingredients to cook dish: " + cookedDish.getDishDescription().getDishName());

        ingredients.forEach(ingredient -> {
            ingredient.setAmount(ingredient.getAmount() - 1);
            ingredientDao.edit(ingredient);
        });
    }

    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    public void setIngredientDao(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }
}