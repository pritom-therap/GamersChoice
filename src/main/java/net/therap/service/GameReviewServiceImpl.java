package net.therap.service;

import net.therap.command.AddReview;
import net.therap.dao.GameDao;
import net.therap.dao.GameReviewDao;
import net.therap.dao.UserDao;
import net.therap.domain.Game;
import net.therap.domain.GameReview;
import net.therap.domain.User;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: pritom
 * Date: 6/4/12
 * Time: 12:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameReviewServiceImpl implements GameReviewService {

    GameDao gameDao;
    UserDao userDao;
    GameReviewDao gameReviewDao;

    public GameDao getGameDao() {
        return gameDao;
    }

    public void setGameDao(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public GameReviewDao getGameReviewDao() {
        return gameReviewDao;
    }

    public void setGameReviewDao(GameReviewDao gameReviewDao) {
        this.gameReviewDao = gameReviewDao;
    }

    public void addReview(AddReview addReview, User user) {



        GameReview gameReview = new GameReview();
        Game game = gameDao.getGameById(addReview.getGameId());

        user = userDao.getUserbyId(user.getUserId());

        gameReview.setUser(user);
        gameReview.setGame(game);
        gameReview.setCons(addReview.getCons());
        gameReview.setDifficulty(addReview.getDifficulty());
        gameReview.setGameLength(addReview.getGameLength());
        gameReview.setPros(addReview.getPros());
        gameReview.setRatingGamePlay(addReview.getRatingGamePlay());
        gameReview.setRatingGraphics(addReview.getRatingGraphics());
        gameReview.setRatingLongevity(addReview.getRatingLongevity());
        gameReview.setRatingPresentation(addReview.getRatingPresentation());
        gameReview.setRatingSound(addReview.getRatingSound());
        gameReview.setReviewDate(new Date());

        gameReviewDao.saveGameReview(gameReview);
        gameDao.updateGameRatings(gameReview);
        userDao.updateUserRatingHistory(gameReview);
        userDao.deleteTrackedGameAfterReview(user,game);
        userDao.updateUserGenreHistory(gameReview);



    }
}
