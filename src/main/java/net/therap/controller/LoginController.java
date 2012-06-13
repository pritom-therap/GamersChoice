package net.therap.controller;

import net.therap.command.Login;
import net.therap.domain.User;
import net.therap.service.UserService;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by IntelliJ IDEA.
 * User: pritom
 * Date: 6/7/12
 * Time: 3:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginController extends SimpleFormController{

    UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        Login login = new Login();

        return login;
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {

        Login login = (Login) command;

        User user = userService.Authenticate(login);

        if(user!=null) {
           HttpSession session = request.getSession();
           session.setAttribute("User", user);
        }
        else {
            response.sendRedirect("/gamerschoice/Login.htm");
        }

        return new ModelAndView("Success");

    }
}
