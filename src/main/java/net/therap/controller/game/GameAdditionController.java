package net.therap.controller.game;

import net.therap.command.GameCmd;
import net.therap.domain.Game;
import net.therap.service.game.GameService;
import org.apache.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * user: pritom
 * Date: 6/5/12
 * Time: 12:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameAdditionController extends SimpleFormController {

    protected final Logger logger = Logger.getLogger(this.getClass());

    private GameService gameService;

    public GameService getGameService() {
        return gameService;
    }

    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }


    protected Object formBackingObject(HttpServlet request) {
        GameCmd gameCmd = new GameCmd();
        return gameCmd;
    }

    protected ModelAndView onSubmit(HttpServletRequest request,
                                    HttpServletResponse response, Object command, BindException errors)
            throws Exception {

        GameCmd gameCmd = (GameCmd) command;


        int gameId = gameService.addGame(gameCmd);

        Game game = gameService.getNewlyAddedGame(gameId, gameCmd);

        return new ModelAndView("Game/Game", "game", game);
    }

    protected Map referenceData(HttpServletRequest request) throws Exception {
        Map referenceData = new HashMap();


        List<String> genreList = new ArrayList<String>();
        genreList.add("FPS");
        genreList.add("TPS");
        genreList.add("Action");
        genreList.add("Adventure");
        genreList.add("Sandbox");
        genreList.add("RPG");
        genreList.add("RTS");
        genreList.add("Horror");
        genreList.add("Hacknslash");
        genreList.add("Stealth");
        genreList.add("Simulation");
        genreList.add("Sports");
        genreList.add("Racing");
        genreList.add("Fighting");
        genreList.add("MMO");
        genreList.add("Puzzle");
        genreList.add("Platformer");

        referenceData.put("genreList", genreList);


        return referenceData;
    }


}
