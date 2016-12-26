package ua.goit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.services.RestaurantInfoService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RestaurantInfoController {
    private RestaurantInfoService infoService;

    @RequestMapping(value = "/restaurant-schema", method = RequestMethod.GET)
    public ModelAndView schema() {
        ModelAndView model = new ModelAndView();
        model.addObject("schemaUrl", infoService.getRestaurantInfo().getRestaurantSchemaUrl());
        model.setViewName("common-info/restaurant-schema");
        return model;
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public ModelAndView contacts() {
        ModelAndView model = new ModelAndView();

        model.addObject("address", infoService.getRestaurantInfo().getAddress());
        model.addObject("email", infoService.getRestaurantInfo().getEmail());
        model.addObject("phone", infoService.getRestaurantInfo().getPhoneNumber());

        model.setViewName("common-info/contacts");
        return model;
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.POST)
    public ModelAndView feedback(HttpServletRequest req) {
        ModelAndView model = new ModelAndView();

        model.addObject("user_name", req.getParameter("user_name"));
        model.addObject("comment", req.getParameter("comment"));

        model.setViewName("common-info/feedback-sent");
        return model;
    }

    @Autowired
    public void setInfoService(RestaurantInfoService infoService) {
        this.infoService = infoService;
    }
}