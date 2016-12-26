package ua.goit.services;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ua.goit.dao.DishDao;
import ua.goit.dao.MenuDao;
import ua.goit.domain.Dish;
import ua.goit.domain.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MenuService {
    private MenuDao menuDao;
    private DishDao dishDao;

    @Transactional
    public Set<Menu> getAllMenus() {
        return menuDao.getAll();
    }

    @Transactional
    public List<String> getMenusNames() {
        return menuDao.getAll()
                .stream().map(Menu::getName)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Transactional
    public Menu findMenuByName(String name) {
        if (StringUtils.isEmpty(name)) throw new RuntimeException("Cannot use null/empty name");

        return menuDao.findByName(name);
    }

    @Transactional
    public void addNewMenu(String menuName, List<Dish> dishes) {
        if (StringUtils.isEmpty(menuName) || dishes == null)
            throw new RuntimeException("Cannot user null/empty values");

        Menu menu = new Menu(menuName, dishes);
        Set<Menu> all = menuDao.getAll();

        if (!all.contains(menu)) {
            menuDao.add(menu);
        } else {
            throw new RuntimeException("Menu already exists");
        }
    }

    @Transactional
    public void removeMenu(Menu menu) {
        if (menu == null) throw new RuntimeException("Cannot use null menu");

        menuDao.remove(menu);
    }

    @Transactional
    public void editMenuName(String menuName, String newName) {
        if (StringUtils.isEmpty(menuName) || StringUtils.isEmpty(newName))
            throw new RuntimeException("Cannot use null values");

        Menu menu = menuDao.findByName(menuName);
        menu.setName(newName);

        menuDao.edit(menu);
    }

    @Transactional
    public void addDishToMenu(String menuName, String[] dishesToAdd) {
        Menu menu = menuDao.findByName(menuName);
        List<Dish> menuDishes = menu.getDishes();

        for (String name : dishesToAdd) {
            Dish dish = dishDao.findByName(name);
            menuDishes.add(dish);
        }

        menu.setDishes(menuDishes);
        menuDao.edit(menu);

    }

    @Transactional
    public void removeDishFromMenu(String menuName, String dishName) {
        Menu menu = menuDao.findByName(menuName);
        List<Dish> dishes = menu.getDishes();

        Dish dish = dishDao.findByName(dishName);
        dishes.remove(dish);

        menu.setDishes(dishes);
        menuDao.edit(menu);
    }

    @Transactional
    public List<Dish> getDishesToAddToMenu(String menuName) {
        List<Dish> menuDishes = menuDao.findByName(menuName).getDishes();
        Set<Dish> allDishes = dishDao.getAll();

        return allDishes.stream()
                .filter(dish -> !menuDishes.contains(dish))
                .collect(Collectors.toList());
    }

    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }
}
