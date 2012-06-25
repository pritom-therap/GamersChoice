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

    private GameService gameService;
    private TrackGameService trackGameService;

    public void setTrackGameService(TrackGameService trackGameService) {
        this.trackGameService = trackGameService;
    }

    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

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
