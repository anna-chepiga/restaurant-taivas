package ua.goit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.domain.Employee;
import ua.goit.domain.Orders;
import ua.goit.services.EmployeeService;
import ua.goit.services.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class OrderController {
    private OrderService orderService;
    private EmployeeService employeeService;

    @RequestMapping(value = "/admin/manage-orders", method = RequestMethod.GET)
    public ModelAndView adminManageOrders() {
        ModelAndView model = new ModelAndView();

        model.addObject("allOrders", orderService.getAllOrders());
        model.addObject("waiters", employeeService.getAllWaiters());
        model.addObject("tableNumbers", orderService.getAllTableNumbers());

        model.setViewName("admin/orders/manage-orders");
        return model;
    }

    @RequestMapping(value = "/admin/filter-by-date", method = RequestMethod.GET)
    public ModelAndView filterByDate(HttpServletRequest request) throws ParseException {
        ModelAndView model = new ModelAndView();

        String dateStr = request.getParameter("date");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date result = formatter.parse(dateStr);

        Set<Orders> orders = orderService.filterOrdersByDate(result);
        model.addObject("orders", orders);

        SimpleDateFormat pageDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        model.addObject("date", pageDateFormat.format(result));

        model.setViewName("admin/orders/filtered-by-date");
        return model;
    }

    @RequestMapping(value = "/admin/filter-by-waiter", method = RequestMethod.GET)
    public ModelAndView filterByWaiter(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();

        String waiterIdStr = request.getParameter("waiterId");
        Long waiterId = Long.parseLong(waiterIdStr);

        Set<Orders> orders = orderService.filterOrdersByWaiter(waiterId);
        model.addObject("orders", orders);

        Employee employee = employeeService.getEmployeeById(waiterId);
        model.addObject("employee", employee);

        model.setViewName("/admin/orders/filtered-by-waiter");
        return model;
    }

    @RequestMapping(value = "/admin/filter-by-table", method = RequestMethod.GET)
    public ModelAndView filterByTable(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();

        String tableNumberStr = request.getParameter("tableNumber");
        Integer tableNumber = Integer.parseInt(tableNumberStr);
        model.addObject("tableNumber", tableNumber);

        Set<Orders> orders = orderService.filterByTableNumber(tableNumber);
        model.addObject("orders", orders);

        model.setViewName("/admin/orders/filtered-by-table");
        return model;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}