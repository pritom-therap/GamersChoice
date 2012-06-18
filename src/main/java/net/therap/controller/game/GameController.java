package net.therap.controller.game;

import net.therap.domain.Game;
import net.therap.domain.User;
import net.therap.service.game.GameService;
import net.therap.service.tracking.TrackGameService;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * user: pritom
 * Date: 6/7/12
 * Time: 9:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class GameController extends SimpleFormController{
    protected final Logger logger = Logger.getLogger(this.getClass());


    GameService gameService;
    TrackGameService trackGameService;



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

        logger.info("Got request from game.htm" + request.getParameter("gameId"));


        int gameId = Integer.parseInt(request.getParameter("gameId"));
        game game = gameService.getGameById(gameId);


        return new ModelAndView("game", "game", game);
    }*/

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {

        int gameId = ServletRequestUtils.getIntParameter(request, "gameId", -1);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");

        Game game = gameService.getGameById(gameId, user);

        return game;

    }


    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,Object command, BindException errors) throws Exception {
        Game game = (Game) command;

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");


        user = trackGameService.addNewTrackedGame(game, user);

        ModelMap modelMap = new ModelMap();

        List<Game> trackedGames = user.getTrackedGames();

        gameService.setGenre(trackedGames);

        modelMap.addAttribute("trackedGames", trackedGames);


        return new ModelAndView("Tracking/TrackedItems", modelMap);



    }
}
