package net.therap.controller.miscellaneous;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * user: tahmid
 * Date: 6/14/12
 * Time: 12:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class AccessErrorController implements Controller{

    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        return new ModelAndView("miscellaneous/AccessError");
    }
}
