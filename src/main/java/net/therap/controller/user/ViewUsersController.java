package net.therap.controller.user;

import net.therap.domain.User;
import net.therap.service.user.UserService;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * user: pritom
 * Date: 6/12/12
 * Time: 6:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class ViewUsersController implements Controller {

    UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        List<User> users = userService.getUsers();

        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("users", users);

        return new ModelAndView("User/Users", modelMap);
    }
}
