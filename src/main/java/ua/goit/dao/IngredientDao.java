package ua.goit.dao;

import ua.goit.domain.Ingredient;

import java.util.Set;

public interface IngredientDao {
    Set<Ingredient> getAll();

    Ingredient findByName(String name);

    void add(Ingredient ingredient);

    void remove(Ingredient ingredient);

    void edit(Ingredient ingredient);
}