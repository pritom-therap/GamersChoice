package net.therap.controller;

import net.therap.domain.Game;
import net.therap.domain.User;
import net.therap.service.GameRecommendationService;
import net.therap.service.GameService;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: pritom
 * Date: 6/12/12
 * Time: 12:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameRecommendationController implements Controller {

    GameRecommendationService gameRecommendationService;
    GameService gameService;

    public GameRecommendationService getGameRecommendationService() {
        return gameRecommendationService;
    }

    public void setGameRecommendationService(GameRecommendationService gameRecommendationService) {
        this.gameRecommendationService = gameRecommendationService;
    }

    public GameService getGameService() {
        return gameService;
    }

    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("User");

        /*if (user == null) {
            httpServletResponse.sendRedirect("/gamerschoice/Login.htm");
        }*/

        List<Game> recommendedGames = gameRecommendationService.getRecommendations(user);

        for (Game recommendedGame : recommendedGames) {

            String genreString = gameService.getGenreAsString(recommendedGame.getGenre());
            recommendedGame.setGenreString(genreString);

        }

        ModelMap modelMap = new ModelMap();

        modelMap.addAttribute("recommendedGames", recommendedGames);

        return new ModelAndView("GamersChoice", modelMap);  //To change body of implemented methods use File | Settings | File Templates.
    }
}
