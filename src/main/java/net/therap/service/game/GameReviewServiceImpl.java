package net.therap.service.game;

import net.therap.command.ReviewCmd;
import net.therap.dao.GameDao;
import net.therap.dao.GameReviewDao;
import net.therap.dao.UserDao;
import net.therap.domain.Game;
import net.therap.domain.GameReview;
import net.therap.domain.User;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * user: pritom
 * Date: 6/4/12
 * Time: 12:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameReviewServiceImpl implements GameReviewService {

    private GameDao gameDao;
    private UserDao userDao;
    private GameReviewDao gameReviewDao;

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

    public void addReview(ReviewCmd reviewCmd, User user) {



        GameReview gameReview = new GameReview();
        Game game = gameDao.getGameById(reviewCmd.getGameId());

        user = userDao.getUserbyId(user.getUserId());

        gameReview.setUser(user);
        gameReview.setGame(game);
        gameReview.setCons(reviewCmd.getCons());
        gameReview.setDifficulty(reviewCmd.getDifficulty());
        gameReview.setGameLength(reviewCmd.getGameLength());
        gameReview.setPros(reviewCmd.getPros());
        gameReview.setRatingGamePlay(reviewCmd.getRatingGamePlay());
        gameReview.setRatingGraphics(reviewCmd.getRatingGraphics());
        gameReview.setRatingLongevity(reviewCmd.getRatingLongevity());
        gameReview.setRatingPresentation(reviewCmd.getRatingPresentation());
        gameReview.setRatingSound(reviewCmd.getRatingSound());
        gameReview.setReviewDate(new Date());

        gameReviewDao.saveGameReview(gameReview);
        gameDao.updateGameRatings(gameReview);
        userDao.updateUserRatingHistory(gameReview);
        userDao.deleteTrackedGameAfterReview(user,game);
        userDao.updateUserGenreHistory(gameReview);



    }

    public List<GameReview> getGamesReviewedByUser(User user) {

        user = userDao.getUserbyId(user.getUserId());


        List<GameReview> gameReviewList = user.getPlayedGames();

        Collections.sort(gameReviewList);

        return gameReviewList;
    }
}
