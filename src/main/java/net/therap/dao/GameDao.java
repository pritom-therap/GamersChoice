package net.therap.dao;

import net.therap.domain.Game;
import net.therap.domain.GameReview;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: pritom
 * Date: 6/3/12
 * Time: 9:42 AM
 * To change this template use File | Settings | File Templates.
 */
public interface GameDao {

     public void updateGameRatings(GameReview gameReview);

     public Game getGameById(int id);

     public List<Game> getGames();

    public int saveGame(Game game);

    public List<Game> getTopGames();
}
