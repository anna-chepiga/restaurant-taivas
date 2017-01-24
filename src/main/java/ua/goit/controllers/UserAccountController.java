package ua.goit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.domain.CookedDish;
import ua.goit.domain.Dish;
import ua.goit.domain.Menu;
import ua.goit.domain.User;
import ua.goit.services.DishService;
import ua.goit.services.MenuService;
import ua.goit.services.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class UserAccountController {
    private UserService userService;
    private MenuService menuService;
    private DishService dishService;

    @RequestMapping(value = "/account/{username}", method = RequestMethod.GET)
    public ModelAndView userAccount(@PathVariable String username) {
        ModelAndView model = new ModelAndView();
        User user = userService.findUserByUsername(username);
        model.addObject("user", user);
        model.setViewName("user-account/account");
        return model;
    }

    @RequestMapping(value = "/account/edit-name/{username}", method = RequestMethod.GET)
    public ModelAndView editNameForm(@PathVariable String username) {
        ModelAndView model = new ModelAndView();
        model.addObject("username", username);
        model.setViewName("user-account/edit-name");
        return model;
    }

    @RequestMapping(value = "/account/edit-name/{username}", method = RequestMethod.POST)
    public ModelAndView processEditName(@PathVariable String username,
                                        HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        String newName = request.getParameter("newName");
        userService.editUserName(username, newName);
        model.setViewName("redirect:../../account/" + username);
        return model;
    }

    @RequestMapping(value = "/account/edit-email/{username}", method = RequestMethod.GET)
    public ModelAndView editEmailForm(@PathVariable String username) {
        ModelAndView model = new ModelAndView();
        model.addObject("username", username);
        model.setViewName("user-account/edit-email");
        return model;
    }

    @RequestMapping(value = "/account/edit-email/{username}", method = RequestMethod.POST)
    public ModelAndView processEditEmail(@PathVariable String username,
                                         HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        String newEmail = request.getParameter("newEmail");
        userService.editUserEmail(username, newEmail);
        model.setViewName("redirect:../../account/" + username);
        return model;
    }

    @RequestMapping(value = "/make-order", method = RequestMethod.GET)
    public ModelAndView makeNewOrder() {
        ModelAndView model = new ModelAndView();
        Date now = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        int hours = c.get(Calendar.HOUR_OF_DAY);

        String menuType = "";

        if (hours >= 1 && hours <= 9) {
            menuType = "Breakfast";
        } else if (hours <= 13) {
            menuType = "Lunch";
        } else if (hours <= 20) {
            menuType = "Dinner";
        } else if (hours <= 24) {
            menuType = "Supper";
        }

        Menu menu = menuService.findMenuByName(menuType);
        model.addObject("currentMenu", menu);

        /*List<String> cart = new ArrayList<>();
        model.addObject("cart", cart);*/

        model.setViewName("user-account/make-order");
        return model;
    }

    @RequestMapping(value = "/add-to-cart/{dishName}", method = RequestMethod.GET)
    public ModelAndView addToCart(@PathVariable String dishName, HttpServletResponse response)
            throws UnsupportedEncodingException {
        ModelAndView model = new ModelAndView();
        String urlDishName = URLEncoder.encode(dishName, "UTF-8");
        response.addCookie(new Cookie("dish", urlDishName));
        model.setViewName("redirect:../make-order");
        return model;
    }

    @RequestMapping(value = "/process-order", method = RequestMethod.POST)
    public ModelAndView processOrder(HttpServletRequest request) throws UnsupportedEncodingException {
        ModelAndView model = new ModelAndView();

        List<CookedDish> dishesForOrder = new ArrayList<>();

        Cookie[] cookies = request.getCookies();
        for (Cookie ck : cookies) {
            if (ck.getName().equals("dish")) {
                String dishName = URLDecoder.decode(ck.getValue(), "UTF-8");
                Dish dishDescription = dishService.findByName(dishName);
                CookedDish cookedDish = new CookedDish(dishDescription);
                dishesForOrder.add(cookedDish);
            }
        }

        //model.setViewName();
        return model;

    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
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