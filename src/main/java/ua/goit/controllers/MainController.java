package ua.goit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.DTO.DishDTO;
import ua.goit.domain.Dish;
import ua.goit.services.DishCategoryService;
import ua.goit.services.DishService;
import ua.goit.services.MenuService;
import ua.goit.services.RestaurantInfoService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    private RestaurantInfoService restaurantInfoService;
    private DishService dishService;
    private MenuService menuService;
    private DishCategoryService categoryService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView model = new ModelAndView();

        List<Dish> popularDishes = new ArrayList<>();
        popularDishes.add(dishService.findByName("Cesar"));
        popularDishes.add(dishService.findByName("Juice"));
        popularDishes.add(dishService.findByName("Fish"));
        model.addObject("popularDishes", popularDishes);

        model.addObject("allDishes", dishService.getAllDishes());
        model.addObject("dishDTO", new DishDTO());

        model.addObject("menuNames", menuService.getMenusNames());
        model.addObject("categoryNames", categoryService.getCategoriesNames());


        model.setViewName("index");
        return model;
    }

    @RequestMapping(value = "/header", method = RequestMethod.GET)
    public ModelAndView header() {
        ModelAndView model = new ModelAndView();
        model.addObject("menuNames", menuService.getMenusNames());
        model.addObject("categoryNames", categoryService.getCategoriesNames());
        model.setViewName("header");
        return model;
    }

    @RequestMapping(value = "/footer", method = RequestMethod.GET)
    public ModelAndView footer() {
        ModelAndView model = new ModelAndView();
        model.addObject("info", restaurantInfoService.getRestaurantInfo());
        model.setViewName("footer");
        return model;
    }

    @Autowired
    public void setRestaurantInfoService(RestaurantInfoService restaurantInfoService) {
        this.restaurantInfoService = restaurantInfoService;
    }

    @Autowired
    public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @Autowired
    public void setCategoryService(DishCategoryService categoryService) {
        this.categoryService = categoryService;
    }
}

// TODO: 15.12.2016 user can make order from cabinet
// TODO: 17.12.2016 разобраться с ордером, просто блюдом и приготовленным блюдом
// TODO: 21.12.2016 еимплои - официант - повар - конструкторы
// TODO: 22.12.2016 infocontroller - send feedback to email
// TODO: 24.12.2016 разобраться с dependecy