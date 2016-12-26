package ua.goit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.DTO.EmployeeDTO;
import ua.goit.domain.Employee;
import ua.goit.services.EmployeeService;
import ua.goit.services.ImageService;
import ua.goit.services.PositionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class EmployeeController {
    private EmployeeService employeeService;
    private PositionService positionService;
    private ImageService imageService;

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public String employees(Map<String, Object> model) {
        model.put("employees", employeeService.findAll());
        return "common-info/employees";
    }

    @RequestMapping(value = "/employee/{name}", method = RequestMethod.GET)
    public ModelAndView employee(@PathVariable String name) {
        ModelAndView model = new ModelAndView();
        Employee employee = employeeService.getEmployeeByName(name);
        model.addObject("employee", employee);

        model.setViewName("common-info/employee");
        return model;
    }

    @RequestMapping(value = "/admin/manage-employees", method = RequestMethod.GET)
    public ModelAndView adminManageEmployees() {
        ModelAndView model = new ModelAndView();

        model.addObject("allEmployees", employeeService.findAll());
        model.setViewName("admin/employees/manage-employees");
        return model;
    }

    @RequestMapping(value = "/admin/add-new-employee", method = RequestMethod.GET)
    public ModelAndView addNewEmployeeForm() {
        ModelAndView model = new ModelAndView();

        model.addObject("employeeDTO", new EmployeeDTO());
        model.addObject("positions", positionService.getPositionsNames());

        model.setViewName("admin/employees/add-new-employee");
        return model;
    }

    @RequestMapping(value = "/admin/add-new-employee", method = RequestMethod.POST)
    public ModelAndView processAddNewEmployee(@ModelAttribute EmployeeDTO employeeDTO,
                                              @RequestParam("photo") CommonsMultipartFile photo,
                                              HttpSession session) {
        ModelAndView model = new ModelAndView();

        employeeService.addNewEmployee(employeeDTO.getFirstName(), employeeDTO.getLastName(),
                employeeDTO.getPosition(), employeeDTO.getSalary());

        Long idOfLastInsertedEmployee = employeeService.getIdOfLastInsertedEmployee();
        String location = "resources/images/employees_photos/";
        String filename = idOfLastInsertedEmployee.toString();
        String photoUrl = imageService.saveImage(session, photo, location, filename);

        Employee employee = employeeService.getEmployeeById(idOfLastInsertedEmployee);

        employee.setPhotoUrl(photoUrl);
        employeeService.editEmployee(employee);

        model.setViewName("redirect:manage-employees");
        return model;
    }

    @RequestMapping(value = "/admin/edit-employee/{id}", method = RequestMethod.GET)
    public ModelAndView editEmployeeForm(@PathVariable Long id) {
        ModelAndView model = new ModelAndView();

        Employee employee = employeeService.getEmployeeById(id);
        model.addObject("employee", employee);

        model.addObject("positions", positionService.getPositionsNames());

        model.setViewName("admin/employees/edit-employee");
        return model;
    }

    @RequestMapping(value = "/admin/edit-employee/{id}", method = RequestMethod.POST)
    public ModelAndView procesEditEmployee(@PathVariable Long id,
                                           HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        Employee employee = employeeService.getEmployeeById(id);

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String position = request.getParameter("position");

        String salaryStr = request.getParameter("salary");
        Integer salary = Integer.parseInt(salaryStr);

        employee.setDetails(firstName, lastName, positionService.findByName(position), salary);
        employeeService.editEmployee(employee);

        model.setViewName("redirect:../manage-employees");
        return model;
    }

    @RequestMapping(value = "/admin/edit-employee-photo/{employeeId}", method = RequestMethod.GET)
    public ModelAndView editEmployeePhotoForm(@PathVariable Long employeeId) {
        ModelAndView model = new ModelAndView();
        model.addObject("employeeId", employeeId);
        model.setViewName("admin/employees/edit-employee-photo");
        return model;
    }

    @RequestMapping(value = "/admin/edit-employee-photo/{employeeId}", method = RequestMethod.POST)
    public ModelAndView processEditEmployeePhoto(@PathVariable Long employeeId,
                                                 @RequestParam("photo") CommonsMultipartFile photo,
                                                 HttpSession session) {
        ModelAndView model = new ModelAndView();

        String photoUrl = employeeService.getEmployeeById(employeeId).getPhotoUrl();
        String path = session.getServletContext().getRealPath("/") + photoUrl;
        imageService.updateImage(photo, path);

        model.setViewName("redirect:../manage-employees");
        return model;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setPositionService(PositionService positionService) {
        this.positionService = positionService;
    }

    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }
}