package ua.goit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.domain.Dish;
import ua.goit.domain.DishCategory;
import ua.goit.services.DishCategoryService;

import java.util.List;

@Controller
public class DishCategoryController {
    private DishCategoryService categoryService;

    @RequestMapping(value = "/category/{categoryName}", method = RequestMethod.GET)
    public ModelAndView category(@PathVariable String categoryName) {
        ModelAndView model = new ModelAndView();

        DishCategory category = categoryService.findByName(categoryName);
        model.addObject("category", category);

        List<Dish> dishes = category.getDishes();
        model.addObject("dishes", dishes);

        model.setViewName("common-info/category");
        return model;
    }

    @Autowired
    public void setCategoryService(DishCategoryService categoryService) {
        this.categoryService = categoryService;
    }
}