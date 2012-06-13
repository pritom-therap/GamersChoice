package net.therap.controller;

import net.therap.dao.UserDao;
import net.therap.domain.User;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: pritom
 * Date: 6/12/12
 * Time: 6:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class ViewUsersController implements Controller{

    UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        List<User> users = userDao.getUsers();

        ModelMap modelMap = new ModelMap();

        modelMap.addAttribute("users", users);

        return new ModelAndView("Users",modelMap);


    }
}
