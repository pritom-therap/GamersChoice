package net.therap.controller.game;

import net.therap.domain.Game;
import net.therap.service.game.GameService;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * user: pritom
 * Date: 6/6/12
 * Time: 5:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class GamesViewController implements Controller {
    protected final Logger logger = Logger.getLogger(this.getClass());

    GameService gameService;

    public GameService getGameService() {
        return gameService;
    }

    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }


    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<Game> gameList = gameService.getGames();

        ModelMap modelMap = new ModelMap();

        modelMap.addAttribute("gameList", gameList);

        return new ModelAndView("/Game/Games", modelMap);

    }
}
