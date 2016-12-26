package ua.goit.services;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.dao.CookedDishDao;
import ua.goit.domain.CookedDish;

import java.util.Set;

public class CookedDishService {
    private CookedDishDao cookedDishDao;

    @Transactional
    public Set<CookedDish> findAllCookedDishes() {
        return cookedDishDao.getAll();
    }

    @Transactional
    public void addNewCookedDish(CookedDish dish) {
        if (dish == null) throw new RuntimeException("Cannot use null dish");

        cookedDishDao.add(dish);
    }

    public void setCookedDishDao(CookedDishDao cookedDishDao) {
        this.cookedDishDao = cookedDishDao;
    }
}