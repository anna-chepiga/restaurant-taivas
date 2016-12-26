package ua.goit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.DTO.UserDTO;
import ua.goit.domain.User;
import ua.goit.domain.UserRole;
import ua.goit.services.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

@Controller
public class UserController {
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView model = new ModelAndView();
        model.addObject("userDTO", new UserDTO());
        model.setViewName("login/login");
        return model;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView processLogin(@ModelAttribute("userDTO") UserDTO userDTO,
                                     HttpServletResponse response) throws NoSuchAlgorithmException {
        ModelAndView model = new ModelAndView();

        String username = userDTO.getUsername();
        String password = userDTO.getPassword();

        UserRole role = userService.checkUserAndGetRole(username, password);

        response.addCookie(new Cookie("username", username));

        if (role.getName().equals("user")) {
            model.setViewName("redirect:account/" + username);
        } else if (role.getName().equals("admin")) {
            model.setViewName("redirect:admin-start-page");
        }

        return model;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        Cookie[] cookies = request.getCookies();
        for (Cookie ck : cookies) {
            if (ck.getName().equals("username")) {
                ck = new Cookie("username", null);
                ck.setMaxAge(0);
                response.addCookie(ck);
            }
        }
        model.setViewName("redirect:/");
        return model;
    }

    @RequestMapping(value = "/admin-start-page", method = RequestMethod.GET)
    public ModelAndView adminStartPage(@CookieValue("username") String username) {
        ModelAndView model = new ModelAndView();

        if (!username.equals("admin")) {
            throw new RuntimeException("You do not have access to this page");
        }

        model.setViewName("admin/admin-start-page");
        return model;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView registerForm() {
        ModelAndView model = new ModelAndView();
        model.addObject("userDTO", new UserDTO());
        model.setViewName("login/register");
        return model;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView processRegister(@ModelAttribute("userDTO") UserDTO userDTO,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws NoSuchAlgorithmException {

        ModelAndView model = new ModelAndView();

        String username = userDTO.getUsername();
        String email = userDTO.getEmail();
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        String name = userDTO.getName();

        userService.addNewUser(username, password, confirmPassword, email, name);

        response.addCookie(new Cookie("username", username));

        model.setViewName("login/registration-successful");
        return model;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}