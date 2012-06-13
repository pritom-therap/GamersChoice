package net.therap.controller;

import net.therap.dao.UserDao;
import net.therap.domain.Game;
import net.therap.domain.GameReview;
import net.therap.domain.User;
import net.therap.service.GameService;
import net.therap.service.TrackGameService;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: pritom
 * Date: 6/7/12
 * Time: 9:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class GameController extends SimpleFormController{
    protected final Logger logger = Logger.getLogger(this.getClass());


    GameService gameService;
    TrackGameService trackGameService;
    UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public TrackGameService getTrackGameService() {
        return trackGameService;
    }

    public void setTrackGameService(TrackGameService trackGameService) {
        this.trackGameService = trackGameService;
    }


    public GameService getGameService() {
        return gameService;
    }

    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }


    /*@Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        logger.info("Got request from Game.htm" + request.getParameter("gameId"));


        int gameId = Integer.parseInt(request.getParameter("gameId"));
        Game game = gameService.getGame(gameId);


        return new ModelAndView("Game", "game", game);
    }*/

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {

        int gameId = Integer.parseInt(request.getParameter("gameId"));
        Game game = gameService.getGame(gameId);

        game.setPlayed(true);
        game.setTracked(false);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");

        user = userDao.getUserbyId(user.getUserId());

        List<GameReview> playedGames = user.getPlayedGames();
        List<Game> trackedGames = user.getTrackedGames();

        logger.info("Size of played ");

        for (GameReview gameReview : playedGames) {

            if (game.getGameName().equals(gameReview.getGame().getGameName()) && game.getPlatform().equals(gameReview.getGame().getPlatform())) {

                game.setPlayed(false);

            }

        }

        for (Game trackedGame : trackedGames) {
            if(game.getGameName().equals(trackedGame.getGameName()) && game.getPlatform().equals(trackedGame.getPlatform())) {
                game.setPlayed(false);
                game.setTracked(true);
            }
        }

        return game;

    }


    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,Object command, BindException errors) throws Exception {
        Game game = (Game) command;

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");

        logger.info(user.getUserName() + " about to track " + game.getGameName()) ;

        user = trackGameService.addNewTrackedGame(game, user);

        ModelMap modelMap = new ModelMap();

        List<Game> trackedGames = user.getTrackedGames();

         for(Game trackedGame : trackedGames) {

            String genreString = gameService.getGenreAsString(trackedGame.getGenre());
            trackedGame.setGenreString(genreString);

        }

        modelMap.addAttribute("trackedGames", trackedGames);


        return new ModelAndView("TrackedItems", modelMap);



    }
}
