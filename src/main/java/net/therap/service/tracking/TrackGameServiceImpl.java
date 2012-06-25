package net.therap.service.tracking;

import net.therap.dao.UserDao;
import net.therap.domain.Game;
import net.therap.domain.User;
import net.therap.utility.GenreMap;

import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * user: pritom
 * Date: 6/10/12
 * Time: 11:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class TrackGameServiceImpl implements TrackGameService {

    private UserDao userDao;
    private GenreMap genreMap;

    public void setGenreMap(GenreMap genreMap) {
        this.genreMap = genreMap;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    public User addNewTrackedGame(Game game, User user) {

        user = userDao.getUserbyId(user.getUserId());

        List<Game> trackedGames = user.getTrackedGames();
        trackedGames.add(game);

        user.setTrackedGames(trackedGames);
        userDao.updateUser(user);
        return user;
    }

    public List<Game> getTrackedGamesByUser(User user) {

        List<Game> trackedGames = user.getTrackedGames();
        setGenre(trackedGames);
        return trackedGames;
    }


    public String getGenreAsString(int genre) {

        String gameGenre = "";


        for (int mask : (Set<Integer>) genreMap.getGenreMap().keySet()) {

            if ((genre & mask) != 0) {
                gameGenre = gameGenre + genreMap.getGenreMap().get(mask) + ", ";
            }

        }

        gameGenre = gameGenre.substring(0, gameGenre.length() - 2);

        return gameGenre;


    }

    public void setGenre(List<Game> games) {

        for (Game game : games) {

            String genreString = getGenreAsString(game.getGenre());
            game.setGenreString(genreString);

        }
    }
}
