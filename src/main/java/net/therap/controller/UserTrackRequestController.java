package net.therap.controller;

import net.therap.dao.UserDao;
import net.therap.domain.Game;
import net.therap.domain.GameReview;
import net.therap.domain.User;
import net.therap.service.GameService;
import net.therap.service.TrackUserService;
import org.apache.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: pritom
 * Date: 6/13/12
 * Time: 9:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserTrackRequestController extends SimpleFormController{
    
    protected final Logger logger = Logger.getLogger(this.getClass());


    UserDao userDao;
    GameService gameService;
    TrackUserService trackUserService;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public GameService getGameService() {
        return gameService;
    }

    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    public TrackUserService getTrackUserService() {
        return trackUserService;
    }

    public void setTrackUserService(TrackUserService trackUserService) {
        this.trackUserService = trackUserService;
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {

        int userId = Integer.parseInt(request.getParameter("userId"));
        User requestedUser = userDao.getUserbyId(userId);

        List<GameReview> reviewedGames = requestedUser.getPlayedGames();
        List<Game> playedGames = new ArrayList<Game>();

        for(GameReview gameReview: reviewedGames){
            Game game = gameReview.getGame();

            game.setGenreString(gameService.getGenreAsString(game.getGenre()));
            playedGames.add(game);
            //gameReview.setGame(game);
        }

        HttpSession session = request.getSession();
        session.setAttribute("playedGames", playedGames);
        return requestedUser;

    }


    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,Object command, BindException errors) throws Exception {

        HttpSession session = request.getSession();

        User user = (User)session.getAttribute("User");

        User trackedUser = (User)command;

        trackUserService.sendRequest(user,trackedUser);


        return new ModelAndView("Success");



    }
    
}
