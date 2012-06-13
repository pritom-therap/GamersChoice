package net.therap.controller;

import net.therap.command.AddGame;
import net.therap.dao.GameDao;
import net.therap.domain.Game;
import net.therap.service.GameService;
import org.apache.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: pritom
 * Date: 6/5/12
 * Time: 12:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class AddGameController extends SimpleFormController {

    protected final Logger logger = Logger.getLogger(this.getClass());

    GameService gameService;
    GameDao gameDao;

    protected Object formBackingObject(HttpServlet request) {
        AddGame addGame = new AddGame();
        return addGame;
    }

    protected ModelAndView onSubmit(HttpServletRequest request,
                                    HttpServletResponse response, Object command, BindException errors)
            throws Exception {

        AddGame addGame = (AddGame) command;
        String fileName = "";


        int gameId = gameService.addGame(addGame);

        Game game = gameDao.getGameById(gameId);

        String genreString = gameService.getGenreAsString(game.getGenre());
        game.setGenreString(genreString);


        byte[] bytes = new byte[addGame.getFile().getInputStream().available()];

        addGame.getFile().getInputStream().read(bytes);
        addGame.getFile().getInputStream().close();


        FileOutputStream fileOutputStream = new FileOutputStream("webapps/gamerschoice/images/game.jpg");

        fileOutputStream.write(bytes);

        fileOutputStream.close();


        return new ModelAndView("Game", "game", game);
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

    public GameService getGameService() {
        return gameService;
    }

    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    public GameDao getGameDao() {
        return gameDao;
    }

    public void setGameDao(GameDao gameDao) {
        this.gameDao = gameDao;
    }
}
