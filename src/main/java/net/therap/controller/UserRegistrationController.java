package net.therap.controller;

import net.therap.command.AddUser;
import net.therap.dao.UserDao;
import net.therap.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.http.HttpServlet;

/**
 * Created by IntelliJ IDEA.
 * User: pritom
 * Date: 6/7/12
 * Time: 1:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserRegistrationController extends SimpleFormController{

    UserDao userDao;
    UserService userService;

    Logger logger = Logger.getLogger(this.getClass());

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    protected Object formBackingObject(HttpServlet request) {
        AddUser addUser = new AddUser();
        return addUser;
    }

    @Override
    protected ModelAndView onSubmit(Object command, BindException errors) throws Exception {
        AddUser addUser = (AddUser) command;

        logger.info(addUser.getUserName());

        userService.saveUser(addUser);

        ModelMap modelMap = new ModelMap();

        modelMap.addAttribute("message","Your account has been created. You can now log in");


        return new ModelAndView("Success", modelMap);
    }
}
