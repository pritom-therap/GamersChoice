package net.therap.controller.authentication;

import net.therap.command.LoginCmd;
import net.therap.domain.User;
import net.therap.service.user.UserService;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by IntelliJ IDEA.
 * user: pritom
 * Date: 6/7/12
 * Time: 3:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginController extends SimpleFormController {

    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        LoginCmd loginCmd = new LoginCmd();
        return loginCmd;
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {

        LoginCmd loginCmd = (LoginCmd) command;
        User user = userService.Authenticate(loginCmd);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("User", user);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("message", "Please put correct Username/Password");
            return new ModelAndView(new RedirectView("/gamerschoice/Login.htm"));
        }
        return new ModelAndView(new RedirectView("/gamerschoice/GamersChoice.htm"));
    }
}
