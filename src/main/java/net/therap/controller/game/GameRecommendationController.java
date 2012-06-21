package net.therap.controller.game;

import net.therap.domain.Game;
import net.therap.domain.User;
import net.therap.service.game.GameRecommendationService;
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
 * Date: 6/12/12
 * Time: 12:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameRecommendationController implements Controller {

    private GameRecommendationService gameRecommendationService;


    public GameRecommendationService getGameRecommendationService() {
        return gameRecommendationService;
    }

    public void setGameRecommendationService(GameRecommendationService gameRecommendationService) {
        this.gameRecommendationService = gameRecommendationService;
    }



    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("User");

        List<Game> recommendedGames = gameRecommendationService.getRecommendations(user);
        boolean gotRecommendation;

        if (recommendedGames.size()!=0) {
            gotRecommendation = true;
        }
        else {
            gotRecommendation=false;
        }

        ModelMap modelMap = new ModelMap();

        modelMap.addAttribute("gotRecommendation", gotRecommendation);
        modelMap.addAttribute("recommendedGames", recommendedGames);

        return new ModelAndView("Game/GamersChoice", modelMap);  //To change body of implemented methods use File | Settings | File Templates.
    }
}
