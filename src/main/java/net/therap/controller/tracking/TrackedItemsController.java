package net.therap.controller.tracking;

import net.therap.domain.Game;
import net.therap.domain.User;
import net.therap.service.tracking.TrackGameService;
import net.therap.service.tracking.TrackUserService;
import net.therap.service.user.UserService;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * user: pritom
 * Date: 6/10/12
 * Time: 3:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class TrackedItemsController implements Controller {


    private UserService userService;
    private TrackGameService trackGameService;
    private TrackUserService trackUserService;

    public void setTrackUserService(TrackUserService trackUserService) {
        this.trackUserService = trackUserService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setTrackGameService(TrackGameService trackGameService) {
        this.trackGameService = trackGameService;
    }

    protected final Logger logger = Logger.getLogger(this.getClass());

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");

        user = userService.getUserById(user.getUserId());

        List<Game> trackedGames = trackGameService.getTrackedGamesByUser(user);

        List<User> trackedUsers = trackUserService.getApprovedUsers(user);

        ModelMap modelMap = new ModelMap();

        modelMap.addAttribute("trackedGames", trackedGames);
        modelMap.addAttribute("trackedUsers",trackedUsers);


        return new ModelAndView("Tracking/TrackedItems", modelMap);


    }

}
