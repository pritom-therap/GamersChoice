package net.therap.controller.miscellaneous;

import net.therap.domain.Game;
import net.therap.service.game.GameService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: pritom
 * Date: 6/21/12
 * Time: 12:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class WelcomeController implements Controller {

    private GameService gameService;

    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        HttpSession session = httpServletRequest.getSession();

        List<Game> topRankedGames = gameService.getTopGames();

        session.setAttribute("topGames", topRankedGames);

        return new ModelAndView("Welcome");
    }
}
