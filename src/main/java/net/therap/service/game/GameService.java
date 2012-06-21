package net.therap.service.game;

import net.therap.command.GameCmd;
import net.therap.domain.Game;
import net.therap.domain.User;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * user: pritom
 * Date: 6/5/12
 * Time: 5:32 PM
 * To change this template use File | Settings | File Templates.
 */
public interface GameService {


    public int addGame(GameCmd gameCmd);

    public String getGenreAsString(int genre);

    public Game getGameById(int gameId, User user);

    public Game getNewlyAddedGame(int newlyAddedGameId, GameCmd addNewGameCmd);

    public List<Game> getGames();

    public void setGenre(List<Game> games);

    public List<Game> getReviewedGames(User user);

    public List<Game> getUnPlayedGames(User user);


    public List<Game> getTopGames();
}
