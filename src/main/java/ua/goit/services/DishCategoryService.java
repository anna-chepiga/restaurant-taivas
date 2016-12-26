package ua.goit.services;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.dao.DishCategoryDao;
import ua.goit.domain.DishCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DishCategoryService {
    private DishCategoryDao dishCategoryDao;

    @Transactional
    public Set<DishCategory> findAll() {
        return dishCategoryDao.getAll();
    }

    @Transactional
    public List<String> getCategoriesNames() {
        return dishCategoryDao.getAll()
                .stream().map(DishCategory::getName)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Transactional
    public DishCategory findByName(String name) {
        return dishCategoryDao.findByName(name);
    }

    public void setDishCategoryDao(DishCategoryDao dishCategoryDao) {
        this.dishCategoryDao = dishCategoryDao;
    }
}
