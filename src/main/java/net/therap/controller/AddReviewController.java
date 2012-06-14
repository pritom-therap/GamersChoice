package net.therap.controller;

import net.therap.command.AddReview;
import net.therap.dao.GameDao;
import net.therap.dao.GameReviewDao;
import net.therap.dao.UserDao;
import net.therap.domain.Game;
import net.therap.domain.User;
import net.therap.service.GameReviewService;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

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
 * User: pritom
 * Date: 5/30/12
 * Time: 11:36 AM
 * To change this template use File | Settings | File Templates.
 */
public class AddReviewController extends SimpleFormController {

    protected final Logger logger = Logger.getLogger(this.getClass());

    GameDao gameDao;
    UserDao userDao;
    GameReviewDao gameReviewDao;
    GameReviewService gameReviewService;

    public GameReviewService getGameReviewService() {
        return gameReviewService;
    }

    public void setGameReviewService(GameReviewService gameReviewService) {
        this.gameReviewService = gameReviewService;
    }

    public GameReviewDao getGameReviewDao() {
        return gameReviewDao;
    }

    public void setGameReviewDao(GameReviewDao gameReviewDao) {
        this.gameReviewDao = gameReviewDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public GameDao getGameDao() {
        return gameDao;
    }

    public void setGameDao(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    /*public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Game game = gameDao.getGameById(1);


        Map<String, Object> myModel = new HashMap<String, Object>();
        ModelMap modelMap = new ModelMap();

        modelMap.addAttribute("game", game);


        return new ModelAndView("welcome", modelMap);

    }*/

    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) {


        AddReview addReview = (AddReview) command;

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");

        gameReviewService.addReview(addReview, user);


        ModelMap modelMap = new ModelMap();

        modelMap.addAttribute("message","Your review has been added");


        return new ModelAndView("Success", modelMap);
    }

    protected Object formBackingObject(HttpServlet request) {
        AddReview addReview = new AddReview();
        return addReview;
    }

    protected Map referenceData(HttpServletRequest request) throws Exception {
        Map referenceData = new HashMap();
        List<Float> scaleList = new ArrayList<Float>();
        for (float i = 1.0f; i <= 10.0; i += 0.5) {
            scaleList.add(i);
        }


        List<Game> games = gameDao.getGames();
        User user = userDao.getUserbyId(1);

        //logger.info(user.getEmail());

        /*HttpSession session = request.getSession();
        session.setAttribute("User", user);*/

        referenceData.put("scaleList", scaleList);
        referenceData.put("games", games);

        return referenceData;
    }
}
