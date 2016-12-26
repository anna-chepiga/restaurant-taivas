package ua.goit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.domain.Ingredient;
import ua.goit.services.IngredientService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class StorageController {
    private IngredientService ingredientService;

    @RequestMapping(value = "/admin/manage-storage", method = RequestMethod.GET)
    public ModelAndView adminManageStorage() {
        ModelAndView model = new ModelAndView();
        model.addObject("ingredients", ingredientService.getAllIngredients());
        model.setViewName("admin/storage/manage-storage");
        return model;
    }

    @RequestMapping(value = "/admin/add-new-ingredient", method = RequestMethod.GET)
    public ModelAndView addNewIngredientForm() {
        ModelAndView model = new ModelAndView();
        model.setViewName("admin/storage/add-new-ingredient");
        return model;
    }

    @RequestMapping(value = "/admin/add-new-ingredient", method = RequestMethod.POST)
    public ModelAndView processAddNewIngredient(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();

        String ingredientName = request.getParameter("ingredientName");
        String amountParameter = request.getParameter("amount");

        Integer amount = Integer.parseInt(amountParameter);
        ingredientService.addNewIngredient(ingredientName, amount);

        model.setViewName("redirect:manage-storage");
        return model;
    }

    @RequestMapping(value = "/admin/find-ingredient", method = RequestMethod.GET)
    public ModelAndView findIngredient(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        String ingredientName = request.getParameter("ingredientName");
        model.setViewName("redirect:ingredient/" + ingredientName);
        return model;
    }

    @RequestMapping(value = "/admin/ingredient/{ingredientName}", method = RequestMethod.GET)
    public ModelAndView ingredient(@PathVariable String ingredientName) {
        ModelAndView model = new ModelAndView();

        Ingredient ingredient = ingredientService.findIngredientByName(ingredientName);
        if (ingredient == null) throw new RuntimeException("Cannot find ingredient '" + ingredientName + "'.");

        model.addObject("ingredient", ingredient);
        model.setViewName("admin/storage/ingredient");
        return model;
    }

    @RequestMapping(value = "/admin/edit-ingredient-name/{ingredientName}", method = RequestMethod.GET)
    public ModelAndView editIngredientNameForm(@PathVariable String ingredientName) {
        ModelAndView model = new ModelAndView();
        model.addObject("oldName", ingredientName);
        model.setViewName("admin/storage/edit-ingredient-name");
        return model;
    }

    @RequestMapping(value = "/admin/edit-ingredient-name/{oldName}", method = RequestMethod.POST)
    public ModelAndView processEditIngredientName(HttpServletRequest request, @PathVariable String oldName) {
        ModelAndView model = new ModelAndView();
        String newName = request.getParameter("newName");
        ingredientService.editIngredientName(oldName, newName);
        model.setViewName("redirect:../manage-storage");
        return model;
    }

    @RequestMapping(value = "/admin/edit-ingredient-amount/{ingredientName}", method = RequestMethod.GET)
    public ModelAndView editIngredientAmountForm(@PathVariable String ingredientName) {
        ModelAndView model = new ModelAndView();
        model.addObject("ingredientName", ingredientName);
        model.addObject("oldAmount", ingredientService.findIngredientByName(ingredientName).getAmount());
        model.setViewName("admin/storage/edit-ingredient-amount");
        return model;
    }

    @RequestMapping(value = "/admin/edit-ingredient-amount/{ingredientName}", method = RequestMethod.POST)
    public ModelAndView processEditIngredientAmount(HttpServletRequest request, @PathVariable String ingredientName) {
        ModelAndView model = new ModelAndView();
        String amountParameter = request.getParameter("newAmount");
        Long amount = Long.parseLong(amountParameter);
        ingredientService.editIngredientAmount(ingredientName, amount);
        model.setViewName("redirect:../manage-storage");
        return model;
    }

    @RequestMapping(value = "admin/delete-ingredient/{ingredientName}", method = RequestMethod.GET)
    public ModelAndView deleteIngredient(@PathVariable String ingredientName) {
        ModelAndView model = new ModelAndView();
        Ingredient ingredient = ingredientService.findIngredientByName(ingredientName);
        ingredientService.removeIngredient(ingredient);
        model.setViewName("redirect:../manage-storage");
        return model;
    }

    @Autowired
    public void setIngredientService(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }
}