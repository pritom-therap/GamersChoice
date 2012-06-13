package net.therap.controller;

import net.therap.dao.GameDao;
import net.therap.domain.Game;
import net.therap.service.GameService;
import org.apache.log4j.Logger;
import org.jmesa.model.TableModel;
import org.jmesa.view.html.component.HtmlColumn;
import org.jmesa.view.html.component.HtmlRow;
import org.jmesa.view.html.component.HtmlTable;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: pritom
 * Date: 6/6/12
 * Time: 5:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class ViewGamesController extends SimpleFormController{
    protected final Logger logger = Logger.getLogger(this.getClass());

    GameDao gameDao;
    GameService gameService;

    public GameDao getGameDao() {
        return gameDao;
    }

    public void setGameDao(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    public GameService getGameService() {
        return gameService;
    }

    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<Game> gameList = gameDao.getGames();

        for(Game game :gameList) {

            String genreString = gameService.getGenreAsString(game.getGenre());
            game.setGenreString(genreString);

        }

        String id = "1";

        TableModel tableModel = new TableModel(id, request);

        tableModel.setItems(gameList);

        HtmlTable htmlTable = new HtmlTable().caption("Games").width("800px");

        HtmlRow htmlRow = new HtmlRow();
        htmlTable.setRow(htmlRow);

        HtmlColumn gameName = new HtmlColumn("Name");
        htmlRow.addColumn( gameName);

        HtmlColumn developer = new HtmlColumn("Developer");
        htmlRow.addColumn(developer);

        HtmlColumn platform = new HtmlColumn("Platform");
        htmlRow.addColumn(platform);

        HtmlColumn genre = new HtmlColumn("Genre(s)");
        htmlRow.addColumn(genre);

        tableModel.setTable(htmlTable);

        String html = tableModel.render();

        ModelMap modelMap = new ModelMap();

        modelMap.addAttribute("gameList", gameList);
        modelMap.addAttribute("table", html);

        return new ModelAndView("Games", modelMap);

    }
}
