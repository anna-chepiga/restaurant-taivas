package ua.goit.dao;

import ua.goit.domain.CookedDish;

import java.util.Set;

public interface CookedDishDao {
    Set<CookedDish> getAll();

    void add(CookedDish cookedDish);
}