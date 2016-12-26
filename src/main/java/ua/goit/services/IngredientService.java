package ua.goit.services;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ua.goit.dao.IngredientDao;
import ua.goit.domain.Ingredient;

import java.util.Set;

public class IngredientService {
    private IngredientDao ingredientDao;

    @Transactional
    public Set<Ingredient> getAllIngredients() {
        return ingredientDao.getAll();
    }

    @Transactional
    public Ingredient findIngredientByName(String name) {
        if (StringUtils.isEmpty(name)) throw new RuntimeException("Cannot use null/empty name");
        return ingredientDao.findByName(name);
    }

    @Transactional
    public void addNewIngredient(String ingredientName, long amount) {
        if (StringUtils.isEmpty(ingredientName) || amount <= 0)
            throw new RuntimeException("Cannot use null/empty name and negative/zero amount");

        Ingredient ingredient = new Ingredient(ingredientName, amount);
        ingredientDao.add(ingredient);
    }

    @Transactional
    public void removeIngredient(Ingredient ingredient) {
        if (ingredient == null) throw new RuntimeException("Cannot use null value");
        ingredientDao.remove(ingredient);
    }

    @Transactional
    public void editIngredientName(String ingredientName, String newName) {
        if (StringUtils.isEmpty(ingredientName) || StringUtils.isEmpty(newName))
            throw new RuntimeException("Cannot use null values");

        Ingredient ingredient = ingredientDao.findByName(ingredientName);
        ingredient.setName(newName);
        ingredientDao.edit(ingredient);
    }

    @Transactional
    public void editIngredientAmount(String ingredientName, Long newAmount) {
        if (StringUtils.isEmpty(ingredientName) || newAmount <= 0)
            throw new RuntimeException("Cannot use null/empty name or zero/negative amount");

        Ingredient ingredient = ingredientDao.findByName(ingredientName);
        ingredient.setAmount(newAmount);
        ingredientDao.edit(ingredient);
    }

    public void setIngredientDao(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }
}