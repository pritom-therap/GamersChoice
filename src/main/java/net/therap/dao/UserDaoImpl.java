package net.therap.dao;

import net.therap.domain.*;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * user: pritom
 * Date: 6/4/12
 * Time: 11:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

    protected final Logger logger = Logger.getLogger(this.getClass());

    public User getUserbyId(int id) {
        return getHibernateTemplate().load(User.class, id);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void updateUserGenreHistory(GameReview gameReview) {
        User user = gameReview.getUser();
        Game game = gameReview.getGame();

        int genre = game.getGenre();

        UserGenreHistory userGenreHistory = user.getUserGenreHistory();

        if ((genre & fpsGenreMask)!=0) {
            userGenreHistory.setFpsGenreCount(userGenreHistory.getFpsGenreCount()+1);
        }
        if ((genre & tpsGenreMask)!=0) {
            userGenreHistory.setTpsGenreCount(userGenreHistory.getTpsGenreCount() + 1);
        }
        if ((genre & actionGenreMask)!=0) {
            userGenreHistory.setActionGenreCount(userGenreHistory.getActionGenreCount() + 1);
        }
        if ((genre & adventureGenreMask)!=0) {
            userGenreHistory.setAdventureGenreCount(userGenreHistory.getAdventureGenreCount() + 1);
        }
        if ((genre & sandboxGenreMask)!=0) {
            userGenreHistory.setSandboxGenreCount(userGenreHistory.getSandboxGenreCount() + 1);
        }
        if ((genre & rpgGenreMask)!=0) {
            userGenreHistory.setRpgGenreCount(userGenreHistory.getRpgGenreCount() + 1);
        }
        if ((genre & rtsGenreMask)!=0) {
            userGenreHistory.setRtsGenreCount(userGenreHistory.getRtsGenreCount() + 1);
        }
        if ((genre & horrorGenreMask)!=0) {
            userGenreHistory.setHorrorGenreCount(userGenreHistory.getHorrorGenreCount() + 1);
        }
        if ((genre & hacknslashGenreMask)!=0) {
            userGenreHistory.setHacknslashGenreCount(userGenreHistory.getHacknslashGenreCount() + 1);
        }
        if ((genre & stealthGenreMask)!=0) {
            userGenreHistory.setStealthGenreCount(userGenreHistory.getStealthGenreCount() + 1);
        }
        if ((genre & simulationGenreMask)!=0) {
            userGenreHistory.setSimulationGenreCount(userGenreHistory.getSimulationGenreCount() + 1);
        }
        if ((genre & sportsGenreMask)!=0) {
            userGenreHistory.setSportsGenreCount(userGenreHistory.getSportsGenreCount() + 1);
        }
        if ((genre & racingGenreMask)!=0) {
            userGenreHistory.setRacingGenreCount(userGenreHistory.getRacingGenreCount() + 1);
        }
        if ((genre & fightingGenreMask)!=0) {
            userGenreHistory.setFightingGenreCount(userGenreHistory.getFightingGenreCount() + 1);
        }
        if ((genre & mmoGenreMask)!=0) {
            userGenreHistory.setMmoGenreCount(userGenreHistory.getMmoGenreCount() + 1);
        }
        if ((genre & puzzleGenreMask)!=0) {
            userGenreHistory.setPuzzleGenreCount(userGenreHistory.getPuzzleGenreCount() + 1);
        }
        if ((genre & platformerGenreMask)!=0) {
            userGenreHistory.setPlatformerGenreCount(userGenreHistory.getPlatformerGenreCount() + 1);
        }

        Session session = getSession();
        session.saveOrUpdate(userGenreHistory);
        session.flush();

    }

    public void updateUserRatingHistory(GameReview gameReview) {

        User user = gameReview.getUser();
        UserRatingHistory userRatingHistory = user.getUserRatingHistory();

        int playedGameCount = user.getPlayedGameCount();

        float updatedAverageRatingPresentation = (userRatingHistory.getAverageRatingPresentation() * playedGameCount + gameReview.getRatingPresentation()) / (playedGameCount + 1);
        userRatingHistory.setAverageRatingPresentation(updatedAverageRatingPresentation);

        float updatedAverageRatingGamePlay = (userRatingHistory.getAverageRatingGamePlay() * playedGameCount + gameReview.getRatingGamePlay()) / (playedGameCount + 1);
        userRatingHistory.setAverageRatingGamePlay(updatedAverageRatingGamePlay);

        float updatedAverageRatingGraphics = (userRatingHistory.getAverageRatingGraphics() * playedGameCount + gameReview.getRatingGraphics()) / (playedGameCount + 1);
        userRatingHistory.setAverageRatingGraphics(updatedAverageRatingGraphics);

        float updatedAverageRatingSound = (userRatingHistory.getAverageRatingSound() * playedGameCount + gameReview.getRatingSound()) / (playedGameCount + 1);
        userRatingHistory.setAverageRatingSound(updatedAverageRatingSound);

        float updatedAverageRatingLongevity = (userRatingHistory.getAverageRatingLongevity() * playedGameCount + gameReview.getRatingLongevity()) / (playedGameCount + 1);
        userRatingHistory.setAverageRatingLongevity(updatedAverageRatingLongevity);

        float updatedAverageGameLength = (float) Math.ceil((userRatingHistory.getAverageRatingGameLength() * playedGameCount + gameReview.getGameLength()) / (playedGameCount + 1));
        userRatingHistory.setAverageRatingGameLength(updatedAverageGameLength);

        int updatedAverageDifficulty = (int) Math.ceil((userRatingHistory.getAverageRatingDifficulty() * playedGameCount + gameReview.getDifficulty()) / (playedGameCount + 1));
        userRatingHistory.setAverageRatingDifficulty(updatedAverageDifficulty);

        user.setPlayedGameCount(playedGameCount + 1);

        Session session = getSession();
        session.saveOrUpdate(userRatingHistory);
        session.flush();

        session.saveOrUpdate(user);
        session.flush();


    }

    public void saveUser(User user) {
        UserGenreHistory userGenreHistory = new UserGenreHistory();
        UserRatingHistory userRatingHistory = new UserRatingHistory();

        userRatingHistory.setUser(user);
        userGenreHistory.setUser(user);

        user.setUserGenreHistory(userGenreHistory);
        user.setUserRatingHistory(userRatingHistory);


        Session session = getSession();
        session.saveOrUpdate(user);
        session.saveOrUpdate(userGenreHistory);
        session.saveOrUpdate(userRatingHistory);
        session.flush();
    }


    public List<User> getUsers() {
        String queryString = "from User";

        List<User> users = getHibernateTemplate().find(queryString);

        return users;
    }

    public void updateUser(User user) {

        Session session = getSession();
        session.update(user);
        session.flush();

    }

    public void deleteTrackedGameAfterReview(User user, Game game) {

        List<Game> trackedGames = user.getTrackedGames();

        logger.info("Size of tracked games: " + trackedGames.size());

        trackedGames.remove(game);

        if (trackedGames.contains(game)) {



            logger.info("found " + game.getGameName() + "in user's list of tracked games");

        }
        else {
            logger.info("could not find " + game.getGameName() + "in user's list of tracked games");

            logger.info("found " + game.getGameName() + "in User's list of tracked games");

        }


        user.setTrackedGames(trackedGames);

    }
}
