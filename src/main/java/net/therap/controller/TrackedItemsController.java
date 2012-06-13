package net.therap.controller;

import net.therap.dao.UserDao;
import net.therap.domain.Game;
import net.therap.domain.GameReview;
import net.therap.domain.User;
import net.therap.service.GameService;
import net.therap.service.TrackUserService;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: pritom
 * Date: 6/10/12
 * Time: 3:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class TrackedItemsController extends SimpleFormController {

    UserDao userDao;
    GameService gameService;
    TrackUserService trackUserService;

    public GameService getGameService() {
        return gameService;
    }

    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public TrackUserService getTrackUserService() {
        return trackUserService;
    }

    public void setTrackUserService(TrackUserService trackUserService) {
        this.trackUserService = trackUserService;
    }

    protected final Logger logger = Logger.getLogger(this.getClass());

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");



        user = userDao.getUserbyId(user.getUserId());

        ModelMap modelMap = new ModelMap();

        List<Game> trackedGames = user.getTrackedGames();

        for (Game trackedGame : trackedGames) {

            String genreString = gameService.getGenreAsString(trackedGame.getGenre());
            trackedGame.setGenreString(genreString);

        }

        List<User> trackedUsers = trackUserService.getApprovedUsers(user);

        for(User trackedUser : trackedUsers){

            List<GameReview> gameReviewList = trackedUser.getPlayedGames();

            Collections.sort(gameReviewList);
        }

        modelMap.addAttribute("trackedGames", trackedGames);
        modelMap.addAttribute("trackedUsers",trackedUsers);


        return new ModelAndView("TrackedItems", modelMap);


    }

}
