package ua.goit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.DTO.DishDTO;
import ua.goit.domain.Dish;
import ua.goit.domain.Ingredient;
import ua.goit.services.DishCategoryService;
import ua.goit.services.DishService;
import ua.goit.services.ImageService;
import ua.goit.services.IngredientService;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@MultipartConfig
public class DishController {
    private DishService dishService;
    private IngredientService ingredientService;
    private DishCategoryService categoryService;
    private ImageService imageService;

    @RequestMapping(value = "/find-dish", method = RequestMethod.GET)
    public ModelAndView findDish(@ModelAttribute("dishTemp") DishDTO dishDTO) {
        ModelAndView model = new ModelAndView();
        String dishName = dishDTO.getDishName();
        model.setViewName("redirect:dish/" + dishName);
        return model;
    }

    @RequestMapping(value = "/dish/{dishName}", method = RequestMethod.GET)
    public ModelAndView dish(@PathVariable String dishName) {
        ModelAndView model = new ModelAndView();

        Dish dish = dishService.findByName(dishName);

        if (dish == null) throw new RuntimeException("Cannot find dish '" + dishName + "'.");

        model.addObject("dish", dish);
        List<Ingredient> ingredients = dish.getIngredients();
        model.addObject("ingredients", ingredients);

        model.setViewName("common-info/dish");
        return model;
    }

    @RequestMapping(value = "/all-dishes", method = RequestMethod.GET)
    public ModelAndView allDishes() {
        ModelAndView model = new ModelAndView();
        model.addObject("allDishes", dishService.getAllDishes());
        model.setViewName("common-info/all-dishes");
        return model;
    }

    @RequestMapping(value = "/filter-by-category", method = RequestMethod.GET)
    public ModelAndView filterByCategory(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();

        String category = request.getParameter("category");
        model.addObject("category", category);

        List<Dish> dishes = dishService.findByCategory(category);
        model.addObject("dishes", dishes);

        model.setViewName("common-info/filtered-by-category");
        return model;
    }

    @RequestMapping(value = "/admin/manage-dishes", method = RequestMethod.GET)
    public ModelAndView adminManageDishes() {
        ModelAndView model = new ModelAndView();
        model.addObject("allDishes", dishService.getAllDishes());
        model.setViewName("admin/dish/manage-dishes");
        return model;
    }

    @RequestMapping(value = "/admin/add-new-dish", method = RequestMethod.GET)
    public ModelAndView addNewDishForm() {
        ModelAndView model = new ModelAndView();

        model.addObject("allIngredients", ingredientService.getAllIngredients());
        model.addObject("dishDTO", new DishDTO());
        model.addObject("categories", categoryService.getCategoriesNames());

        model.setViewName("admin/dish/add-new-dish");
        return model;
    }

    @RequestMapping(value = "/admin/add-new-dish", method = RequestMethod.POST)
    public ModelAndView processAddNewDish(@ModelAttribute("dishDTO") DishDTO dishDTO,
                                          HttpServletRequest request,
                                          @RequestParam("photo") CommonsMultipartFile photo,
                                          HttpSession session) {
        ModelAndView model = new ModelAndView();

        String location = "resources/images/dish_photos/";
        String filename = dishDTO.getDishName();
        String photoUrl = imageService.saveImage(session, photo, location, filename);

        List<Ingredient> dishIngredients = new ArrayList<>();

        dishService.addNewDish(dishDTO.getDishName(), dishDTO.getCategory(),
                dishDTO.getPrice(), dishDTO.getWeight(), photoUrl, dishIngredients);

        String[] ingredientsToAdd = request.getParameterValues("ingredient");

        if (ingredientsToAdd != null) {
            dishService.addIngredientToDish(dishDTO.getDishName(), ingredientsToAdd);
        }

        model.setViewName("redirect:manage-dishes");
        return model;
    }

    @RequestMapping(value = "/admin/edit-dish-photo/{dishName}", method = RequestMethod.GET)
    public ModelAndView editDishPhotoForm(@PathVariable String dishName) {
        ModelAndView model = new ModelAndView();
        model.addObject("dishName", dishName);
        model.setViewName("admin/dish/edit-dish-photo");
        return model;
    }

    @RequestMapping(value = "/admin/edit-dish-photo/{dishName}", method = RequestMethod.POST)
    public ModelAndView processEditDishPhoto(@PathVariable String dishName,
                                             @RequestParam("photo") CommonsMultipartFile photo,
                                             HttpSession session) {
        ModelAndView model = new ModelAndView();

        String photoUrl = dishService.findByName(dishName).getPhotoUrl();
        String path = session.getServletContext().getRealPath("/") + photoUrl;
        imageService.updateImage(photo, path);

        model.setViewName("redirect:../manage-dishes");
        return model;
    }

    @RequestMapping(value = "/admin/edit-dish/{dishId}", method = RequestMethod.GET)
    public ModelAndView editDishForm(@PathVariable Long dishId) {
        ModelAndView model = new ModelAndView();

        model.addObject("dish", dishService.findById(dishId));
        model.addObject("categories", categoryService.getCategoriesNames());

        model.setViewName("admin/dish/edit-dish");
        return model;
    }

    @RequestMapping(value = "/admin/edit-dish/{dishId}", method = RequestMethod.POST)
    public ModelAndView processEditDish(@PathVariable Long dishId,
                                        HttpServletRequest request) {
        ModelAndView model = new ModelAndView();

        Dish dish = dishService.findById(dishId);
        String name = request.getParameter("name");
        String category = request.getParameter("category");

        String weightStr = request.getParameter("weight");
        Integer weight = Integer.parseInt(weightStr);

        String priceStr = request.getParameter("price");
        Double price = Double.parseDouble(priceStr);

        dish.setDetails(name, categoryService.findByName(category), weight, price);
        dishService.editDish(dish);

        model.setViewName("redirect:../manage-dishes");
        return model;
    }

    @RequestMapping(value = "/admin/delete-dish/{dishName}", method = RequestMethod.GET)
    public ModelAndView deleteDish(@PathVariable String dishName) {
        ModelAndView model = new ModelAndView();
        Dish dish = dishService.findByName(dishName);
        dishService.removeDish(dish);

        model.setViewName("redirect:../manage-dishes");
        return model;
    }

    @RequestMapping(value = "/admin/add-ingredient-to-dish/{dishName}", method = RequestMethod.GET)
    public ModelAndView addIngredientForm(@PathVariable String dishName) {
        ModelAndView model = new ModelAndView();

        model.addObject("ingredientsToAdd", dishService.getIngredientsToAddToDish(dishName));
        model.addObject("dishName", dishName);

        model.setViewName("admin/dish/add-ingredient-to-dish");
        return model;
    }

    @RequestMapping(value = "/admin/add-ingredient-to-dish/{dishName}", method = RequestMethod.POST)
    public ModelAndView processAddIngredient(HttpServletRequest request, @PathVariable String dishName) {
        ModelAndView model = new ModelAndView();

        String[] ingredientsToAdd = request.getParameterValues("ingredient");
        dishService.addIngredientToDish(dishName, ingredientsToAdd);

        model.setViewName("redirect:../manage-dishes");
        return model;
    }

    @RequestMapping(value = "/admin/remove-ingredient-from-dish/{dishName}/{ingrName}")
    public ModelAndView removeIngredientFromDish(@PathVariable String dishName, @PathVariable String ingrName) {
        ModelAndView model = new ModelAndView();
        dishService.removeIngredientFromDish(dishName, ingrName);
        model.setViewName("redirect:../../manage-dishes");
        return model;
    }

    @Autowired
    public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }

    @Autowired
    public void setIngredientService(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @Autowired
    public void setCategoryService(DishCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }
}