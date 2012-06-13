package net.therap.service;

import net.therap.dao.GameDao;
import net.therap.dao.UserDao;
import net.therap.domain.Game;
import net.therap.domain.User;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: pritom
 * Date: 6/10/12
 * Time: 11:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class TrackGameServiceImpl implements TrackGameService{

    UserDao userDao;
    GameDao gameDao;

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

    public User addNewTrackedGame(Game game, User user) {

        user = userDao.getUserbyId(user.getUserId());

        List<Game> trackedGames = user.getTrackedGames();

        trackedGames.add(game);

        user.setTrackedGames(trackedGames);


        /*List<User> trackingUsers = game.getTrackingUsers();

        trackingUsers.add(user);

        game.setTrackingUsers(trackingUsers);
        */
        userDao.updateUser(user);
        //gameDao.saveGame(game);

        return user;

    }
}
