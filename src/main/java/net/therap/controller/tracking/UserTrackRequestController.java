package net.therap.controller.tracking;

import net.therap.domain.Game;
import net.therap.domain.User;
import net.therap.service.game.GameService;
import net.therap.service.tracking.TrackUserService;
import net.therap.service.user.UserService;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * user: pritom
 * Date: 6/13/12
 * Time: 9:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserTrackRequestController extends SimpleFormController {
    protected final Logger logger = Logger.getLogger(this.getClass());

    private GameService gameService;
    private TrackUserService trackUserService;
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    public void setTrackUserService(TrackUserService trackUserService) {
        this.trackUserService = trackUserService;
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {

        int userId = ServletRequestUtils.getIntParameter(request,"userId",-1);
        User requestedUser = userService.getUserById(userId);
        List<Game> playedGames = gameService.getReviewedGames(requestedUser);

        HttpSession session = request.getSession();
        session.setAttribute("playedGames", playedGames);

        User currentUser = (User) session.getAttribute("User");
        currentUser = userService.getUserById(currentUser.getUserId());

        trackUserService.setTrackStatus(currentUser, requestedUser);

        return requestedUser;
    }


    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");
        User trackedUser = (User) command;
        trackUserService.addRequest(user, trackedUser);

        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("userId", trackedUser.getUserId());

        return new ModelAndView(new RedirectView("/gamerschoice/User.htm"), modelMap);
    }
}
