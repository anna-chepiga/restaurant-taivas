package ua.goit.dao;

import ua.goit.domain.DishCategory;

import java.util.Set;

public interface DishCategoryDao {
    Set<DishCategory> getAll();

    DishCategory findByName(String name);
}