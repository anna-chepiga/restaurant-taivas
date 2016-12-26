package ua.goit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.DTO.MenuDTO;
import ua.goit.domain.Dish;
import ua.goit.domain.Menu;
import ua.goit.services.DishService;
import ua.goit.services.MenuService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MenuController {
    private MenuService menuService;
    private DishService dishService;

    @RequestMapping(value = "/menu/{menuName}", method = RequestMethod.GET)
    public ModelAndView menu(@PathVariable String menuName) {
        ModelAndView model = new ModelAndView();

        Menu menu = menuService.findMenuByName(menuName);
        model.addObject("menu", menu);

        List<Dish> dishes = menu.getDishes();
        model.addObject("dishes", dishes);

        model.setViewName("common-info/menu");
        return model;
    }

    @RequestMapping(value = "/admin/manage-menus", method = RequestMethod.GET)
    public ModelAndView adminManageMenus() {
        ModelAndView model = new ModelAndView();
        model.addObject("allMenus", menuService.getAllMenus());
        model.setViewName("admin/menu/manage-menus");
        return model;
    }

    @RequestMapping(value = "/admin/edit-menu-name/{menuName}", method = RequestMethod.GET)
    public ModelAndView editMenuNameForm(@PathVariable String menuName) {
        ModelAndView model = new ModelAndView();
        model.addObject("oldName", menuName);
        model.addObject("menuDTO", new MenuDTO());
        model.setViewName("admin/menu/edit-menu-name");
        return model;
    }

    @RequestMapping(value = "/admin/edit-menu-name/{oldName}", method = RequestMethod.POST)
    public ModelAndView processEditMenuName(@ModelAttribute MenuDTO menuDTO, @PathVariable String oldName) {
        ModelAndView model = new ModelAndView();
        String newName = menuDTO.getMenuName();
        menuService.editMenuName(oldName, newName);

        model.setViewName("redirect:../manage-menus");
        return model;
    }

    @RequestMapping(value = "/admin/delete-menu/{menuName}", method = RequestMethod.GET)
    public ModelAndView deleteMenu(@PathVariable String menuName) {
        ModelAndView model = new ModelAndView();
        Menu menu = menuService.findMenuByName(menuName);
        menuService.removeMenu(menu);

        model.setViewName("redirect:../manage-menus");
        return model;
    }

    @RequestMapping(value = "/admin/remove-dish-from-menu/{menuName}/{dishName}")
    public ModelAndView removeDishFromMenu(@PathVariable String menuName, @PathVariable String dishName) {
        ModelAndView model = new ModelAndView();
        menuService.removeDishFromMenu(menuName, dishName);
        model.setViewName("redirect:../../manage-menus");
        return model;
    }

    @RequestMapping(value = "/admin/add-dish-to-menu/{menuName}", method = RequestMethod.GET)
    public ModelAndView addDishForm(@PathVariable String menuName) {
        ModelAndView model = new ModelAndView();

        model.addObject("dishesToAdd", menuService.getDishesToAddToMenu(menuName));
        model.addObject("menuName", menuName);

        model.setViewName("admin/menu/add-dish-to-menu");
        return model;
    }

    @RequestMapping(value = "/admin/add-dish-to-menu/{menuName}", method = RequestMethod.POST)
    public ModelAndView processAddDish(HttpServletRequest request, @PathVariable String menuName) {
        ModelAndView model = new ModelAndView();

        String[] dishesToAdd = request.getParameterValues("dish");
        menuService.addDishToMenu(menuName, dishesToAdd);

        model.setViewName("redirect:../manage-menus");
        return model;
    }

    @RequestMapping(value = "/admin/add-new-menu", method = RequestMethod.GET)
    public ModelAndView addNewMenuForm() {
        ModelAndView model = new ModelAndView();
        model.addObject("allDishes", dishService.getAllDishes());
        model.addObject("menuDTO", new MenuDTO());
        model.setViewName("admin/menu/add-new-menu");
        return model;
    }

    @RequestMapping(value = "/admin/add-new-menu", method = RequestMethod.POST)
    public ModelAndView processAddNewMenu(@ModelAttribute("menuDTO") MenuDTO menuDTO, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();

        String menuName = menuDTO.getMenuName();
        List<Dish> menuDishes = new ArrayList<>();

        menuService.addNewMenu(menuName, menuDishes);

        String[] dishesToAdd = request.getParameterValues("dish");
        if (dishesToAdd != null) {
            menuService.addDishToMenu(menuName, dishesToAdd);
        }

        model.setViewName("redirect:manage-menus");
        return model;
    }

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @Autowired
    public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }
}