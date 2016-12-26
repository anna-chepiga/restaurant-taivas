package ua.goit.dao;

import ua.goit.domain.Dish;
import ua.goit.domain.DishCategory;

import java.util.List;
import java.util.Set;

public interface DishDao {
    Set<Dish> getAll();

    Dish findByName(String name);

    Dish findById(Long id);

    List<Dish> findByCategory(DishCategory category);

    void add(Dish dish);

    void remove(Dish dish);

    void edit(Dish dish);
}