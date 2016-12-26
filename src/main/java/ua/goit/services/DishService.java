package ua.goit.services;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ua.goit.dao.DishCategoryDao;
import ua.goit.dao.DishDao;
import ua.goit.dao.IngredientDao;
import ua.goit.domain.Dish;
import ua.goit.domain.DishCategory;
import ua.goit.domain.Ingredient;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DishService {
    private DishDao dishDao;
    private DishCategoryDao dishCategoryDao;
    private IngredientDao ingredientDao;

    @Transactional
    public Set<Dish> getAllDishes() {
        return dishDao.getAll();
    }

    @Transactional
    public Dish findByName(String name) {
        if (name == null || name.equals("")) throw new RuntimeException("Cannot use empty name");

        return dishDao.findByName(name);
    }

    @Transactional
    public Dish findById(Long id) {
        if (id <= 0) throw new RuntimeException("Id cannot be zero or negative");
        return dishDao.findById(id);
    }

    @Transactional
    public List<Dish> findByCategory(String categoryName) {
        if (StringUtils.isEmpty(categoryName))
            throw new RuntimeException("Cannot use null/empty values");

        DishCategory category = dishCategoryDao.findByName(categoryName);
        return dishDao.findByCategory(category);
    }

    @Transactional
    public void addNewDish(String dishName, String categoryName, double price, Integer weight,
                           String photoUrl, List<Ingredient> ingredients) {
        if (StringUtils.isEmpty(dishName) || StringUtils.isEmpty(categoryName) || price <= 0 ||
                weight <= 0 || StringUtils.isEmpty(photoUrl) || ingredients == null)
            throw new RuntimeException("Cannot use null/empty values");

        DishCategory category = dishCategoryDao.findByName(categoryName);
        Dish dish = new Dish(dishName, category, price, weight, photoUrl, ingredients);

        Set<Dish> all = dishDao.getAll();

        if (!all.contains(dish)) {
            dishDao.add(dish);
        } else {
            throw new RuntimeException("Dish already exists");
        }
    }

    @Transactional
    public void removeDish(Dish dish) {
        if (dish == null) throw new RuntimeException("Cannot use null dish");

        dishDao.remove(dish);
    }

    @Transactional
    public void editDish(Dish dish) {
        if (dish == null)  throw new RuntimeException("Dish cannot be null");
        dishDao.edit(dish);
    }

    @Transactional
    public void addIngredientToDish(String dishName, String[] ingredientsToAdd) {
        Dish dish = dishDao.findByName(dishName);
        List<Ingredient> dishIngredients = dish.getIngredients();

        for (String ingrName : ingredientsToAdd) {
            Ingredient ingredient = ingredientDao.findByName(ingrName);
            dishIngredients.add(ingredient);
        }

        dish.setIngredients(dishIngredients);
        dishDao.edit(dish);
    }

    @Transactional
    public void removeIngredientFromDish(String dishName, String ingredientName) {
        Dish dish = dishDao.findByName(dishName);
        List<Ingredient> ingredients = dish.getIngredients();

        Ingredient ingredient = ingredientDao.findByName(ingredientName);
        ingredients.remove(ingredient);

        dish.setIngredients(ingredients);
        dishDao.edit(dish);
    }

    @Transactional
    public List<Ingredient> getIngredientsToAddToDish(String dishName) {
        List<Ingredient> dishIngredients = dishDao.findByName(dishName).getIngredients();
        Set<Ingredient> allIngredients = ingredientDao.getAll();

        return allIngredients.stream()
                .filter(ingredient -> !dishIngredients.contains(ingredient))
                .collect(Collectors.toList());
    }

    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    public void setDishCategoryDao(DishCategoryDao dishCategoryDao) {
        this.dishCategoryDao = dishCategoryDao;
    }

    public void setIngredientDao(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }
}