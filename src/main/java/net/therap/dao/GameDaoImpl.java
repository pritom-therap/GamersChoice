package net.therap.dao;

import net.therap.domain.Game;
import net.therap.domain.GameReview;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * user: pritom
 * Date: 5/31/12
 * Time: 4:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameDaoImpl extends HibernateDaoSupport implements GameDao {

    protected final Logger logger = Logger.getLogger(this.getClass());
    DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public Game getGameById(int id) {

        return getHibernateTemplate().load(Game.class, id);

    }

    public void updateGameRatings(GameReview gameReview) {

        Game game = gameReview.getGame();
        int voteCount = game.getVoteCount();

        float updatedRatingPresentation = (game.getRatingPresentation() * voteCount + gameReview.getRatingPresentation()) / (voteCount + 1);
        game.setRatingPresentation(updatedRatingPresentation);

        float updatedRatingGamePlay = (game.getRatingGamePlay() * voteCount + gameReview.getRatingGamePlay()) / (voteCount + 1);
        game.setRatingGamePlay(updatedRatingGamePlay);

        float updatedRatingGraphics = (game.getRatingGraphics() * voteCount + gameReview.getRatingGraphics()) / (voteCount + 1);
        game.setRatingGraphics(updatedRatingGraphics);

        float updatedRatingSound = (game.getRatingSound() * voteCount + gameReview.getRatingSound()) / (voteCount + 1);
        game.setRatingSound(updatedRatingSound);

        float updatedRatingLongevity = (game.getRatingLongevity() * voteCount + gameReview.getRatingLongevity()) / (voteCount + 1);
        game.setRatingLongevity(updatedRatingLongevity);

        float updatedGameLength = (float) Math.ceil((game.getGameLength() * voteCount + gameReview.getGameLength()) / (voteCount + 1));
        game.setGameLength(updatedGameLength);

        int updatedDifficulty = (int)Math.ceil((game.getDifficulty() * voteCount + gameReview.getDifficulty())/(voteCount+1));
        game.setDifficulty(updatedDifficulty);

        game.setVoteCount(voteCount+1);

        Session session = getSession();
        session.saveOrUpdate(game);
        session.flush();
    }

    public List<Game> getGames() {

        String queryString = "from Game";
        List<Game> games = getHibernateTemplate().find(queryString);
        return games;
    }

    public int saveGame(Game game){

        Session session = getSession();
        session.saveOrUpdate(game);
        session.flush();

        return game.getGameId();
    }

    public List<Game> getTopGames() {

        String queryString = "from Game";

        List<Game> games = getHibernateTemplate().find(queryString);
        Collections.sort(games);

        int subListSize = Math.min(5,games.size());

        List<Game> topGames = games.subList(0,subListSize);

        return topGames;
    }
}
