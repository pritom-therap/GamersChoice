package net.therap.controller.game;

import net.therap.domain.GameReview;
import net.therap.domain.User;
import net.therap.service.game.GameReviewService;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * user: tahmid
 * Date: 6/14/12
 * Time: 6:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameReviewController implements Controller {


    private GameReviewService gameReviewService;
    protected final Logger logger = Logger.getLogger(this.getClass());

    public GameReviewService getGameReviewService() {
        return gameReviewService;
    }

    public void setGameReviewService(GameReviewService gameReviewService) {
        this.gameReviewService = gameReviewService;
    }

    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("User");

        List<GameReview> gameReviewList = gameReviewService.getGamesReviewedByUser(user);

        ModelMap modelMap = new ModelMap();

        modelMap.put("playedGames", gameReviewList);

        return new ModelAndView("Game/GameReviews",modelMap);


    }
}
