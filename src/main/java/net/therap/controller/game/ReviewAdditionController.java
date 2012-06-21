package net.therap.controller.game;

import net.therap.command.ReviewCmd;
import net.therap.domain.Game;
import net.therap.domain.User;
import net.therap.service.game.GameReviewService;
import net.therap.service.game.GameService;
import org.apache.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * user: pritom
 * Date: 5/30/12
 * Time: 11:36 AM
 * To change this template use File | Settings | File Templates.
 */
public class ReviewAdditionController extends SimpleFormController {

    protected final Logger logger = Logger.getLogger(this.getClass());

    GameService gameService;
    GameReviewService gameReviewService;

    public GameReviewService getGameReviewService() {
        return gameReviewService;
    }

    public void setGameReviewService(GameReviewService gameReviewService) {
        this.gameReviewService = gameReviewService;
    }

    public GameService getGameService() {
        return gameService;
    }

    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) {


        ReviewCmd reviewCmd = (ReviewCmd) command;

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");

        gameReviewService.addReview(reviewCmd, user);

        return new ModelAndView(new RedirectView("/gamerschoice/GameReviews.htm"));
    }

    protected Object formBackingObject(HttpServlet request) {
        ReviewCmd reviewCmd = new ReviewCmd();
        return reviewCmd;
    }

    protected Map referenceData(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");

        Map referenceData = new HashMap();
        List<Float> scaleList = new ArrayList<Float>();
        for (float i = 1.0f; i <= 10.0; i += 0.5) {
            scaleList.add(i);
        }


        List<Game> games = gameService.getUnPlayedGames(user);

        referenceData.put("scaleList", scaleList);
        referenceData.put("games", games);

        return referenceData;
    }
}
