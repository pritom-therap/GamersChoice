package net.therap.filter;

import net.therap.domain.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * user: pritom
 * Date: 6/7/12
 * Time: 4:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginFilter implements Filter {

    protected final Logger logger = Logger.getLogger(this.getClass());



    public void init(FilterConfig filterConfig) throws ServletException {
        //To change body of implemented methods use File | Settings | File Templates.


    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        String path = ((HttpServletRequest) servletRequest).getRequestURI();


        if (path.contains("Login") || path.contains("Register") || path.contains("Welcome")) {

           filterChain.doFilter(servletRequest, servletResponse);

        } else {
            User user = (User) session.getAttribute("User");

            if (user == null) {

                logger.info("Found user to be null");
                session.setAttribute("message", "Sorry, you need to login to view this page");


                logger.info("Found User to be null");

                ((HttpServletResponse) servletResponse).sendRedirect("/gamerschoice/Login.htm");
                return;
            }


            if (user!=null && path.contains("AddNewGame") && user.getStatus().equals("U")) {
                 ((HttpServletResponse) servletResponse).sendRedirect("/gamerschoice/AccessError.htm");
            }


            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    public void destroy() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
