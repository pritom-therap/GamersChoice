package net.therap.controller.authentication;

import net.therap.command.UserCmd;
import net.therap.service.user.UserService;
import org.apache.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServlet;

/**
 * Created by IntelliJ IDEA.
 * user: pritom
 * Date: 6/7/12
 * Time: 1:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserRegistrationController extends SimpleFormController{


    private UserService userService;

    protected final Logger logger = Logger.getLogger(this.getClass());



    public void setUserService(UserService userService) {
        this.userService = userService;
    }



    protected Object formBackingObject(HttpServlet request){
        UserCmd userCmd = new UserCmd();
        return userCmd;
    }

    @Override
    protected ModelAndView onSubmit(Object command, BindException errors) throws Exception {
        UserCmd userCmd = (UserCmd) command;

        userService.saveUser(userCmd);

        return new ModelAndView(new RedirectView("/gamerschoice/Login.htm"));
    }
}
