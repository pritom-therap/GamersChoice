package net.therap.controller.authentication;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by IntelliJ IDEA.
 * user: pritom
 * Date: 6/11/12
 * Time: 12:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class LogoutController implements Controller {

    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        session.removeAttribute("User");
        session.invalidate();

        return new ModelAndView(new RedirectView("/gamerschoice/Login.htm"));
    }
}
