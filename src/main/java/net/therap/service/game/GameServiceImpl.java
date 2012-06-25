package net.therap.service.game;

import net.therap.command.GameCmd;
import net.therap.dao.GameDao;
import net.therap.dao.UserDao;
import net.therap.domain.Game;
import net.therap.domain.GameReview;
import net.therap.domain.User;
import net.therap.utility.GenreMap;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * user: pritom
 * Date: 6/5/12
 * Time: 5:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameServiceImpl implements GameService {
    protected final Logger logger = Logger.getLogger(this.getClass());

    private GameDao gameDao;
    private GenreMap genreMap;
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setGameDao(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    public void setGenreMap(GenreMap genreMap) {
        this.genreMap = genreMap;
    }

    public int addGame(GameCmd gameCmd) {

        Game game = new Game();
        game.setGameName(gameCmd.getGameName());
        game.setPlatform(gameCmd.getPlatform());
        game.setSynopsis(gameCmd.getSynopsis());
        game.setDeveloper(gameCmd.getDeveloper());

        MultipartFile multipartFile = gameCmd.getFile();
        Blob blob = null;
        try {
            blob = Hibernate.createBlob(multipartFile.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        game.setScreenShot(blob);

        String[] genreList = gameCmd.getGenre();

        int genre = getGenreAsInteger(genreList);

        game.setGenre(genre);

        game.setRatingPresentation(5.0f);
        game.setRatingGamePlay(5.0f);
        game.setRatingGraphics(5.0f);
        game.setRatingSound(5.0f);
        game.setRatingLongevity(5.0f);
        game.setDifficulty(3);
        game.setGameLength(10);

        game.setStatus("A");

        game.setVoteCount(0);

        game.setReleaseDate(new Date());


        int gameId = gameDao.saveGame(game);

        return gameId;
    }

    public int getGenreAsInteger(String[] genreList) {
        int genre = 0;


        for (String genreName : genreList) {

            if (genreMap.getGenreMap().containsValue(genreName)) {
                genre |= (Integer) genreMap.getGenreMap().getKey(genreName);
            }

        }
        return genre;
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


    public Game getGameById(int gameId, User user) {

        Game game = gameDao.getGameById(gameId);

        String genreString = getGenreAsString(game.getGenre());
        game.setGenreString(genreString);


        try {
            byte[] bytes = new byte[(int) game.getScreenShot().length()];


            game.getScreenShot().getBinaryStream().read(bytes);
            game.getScreenShot().getBinaryStream().close();


            FileOutputStream fileOutputStream = new FileOutputStream("webapps/gamerschoice/images/screenshot.jpg");

            fileOutputStream.write(bytes);

            fileOutputStream.close();

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        game.setPlayed(true);
        game.setTracked(false);

        user = userDao.getUserbyId(user.getUserId());

        List<GameReview> playedGames = user.getPlayedGames();
        List<Game> trackedGames = user.getTrackedGames();

        for (GameReview gameReview : playedGames) {

            if (game.getGameName().equals(gameReview.getGame().getGameName()) && game.getPlatform().equals(gameReview.getGame().getPlatform())) {

                game.setPlayed(false);

            }

        }

        for (Game trackedGame : trackedGames) {
            if (game.getGameName().equals(trackedGame.getGameName()) && game.getPlatform().equals(trackedGame.getPlatform())) {
                game.setPlayed(false);
                game.setTracked(true);
            }
        }

        return game;
    }

    public void setGenre(List<Game> games) {

        for (Game game : games) {

            String genreString = getGenreAsString(game.getGenre());
            game.setGenreString(genreString);

        }
    }

    public List<Game> getGames() {

        List<Game> games = gameDao.getGames();

        setGenre(games);

        return games;

    }


    public List<Game> getReviewedGames(User user) {
        List<GameReview> reviewedGames = user.getPlayedGames();
        List<Game> playedGames = new ArrayList<Game>();

        for (GameReview gameReview : reviewedGames) {
            Game game = gameReview.getGame();

            game.setGenreString(getGenreAsString(game.getGenre()));
            playedGames.add(game);
        }
        return playedGames;
    }

    public Game getNewlyAddedGame(int newlyAddedGameId, GameCmd addNewGameCmd) {
        Game game = gameDao.getGameById(newlyAddedGameId);

        String genreString = getGenreAsString(game.getGenre());
        game.setGenreString(genreString);
        game.setPlayed(true);

        try {
            byte[] bytes = new byte[addNewGameCmd.getFile().getInputStream().available()];
            addNewGameCmd.getFile().getInputStream().read(bytes);
            addNewGameCmd.getFile().getInputStream().close();

            FileOutputStream fileOutputStream = new FileOutputStream("webapps/gamerschoice/images/screenshot.jpg");
            fileOutputStream.write(bytes);
            fileOutputStream.close();
        } catch (IOException Ex) {
            throw new RuntimeException(Ex);
        }
        return game;
    }


    public List<Game> getTopGames() {
        return gameDao.getTopGames();
    }

    public List<Game> getUnPlayedGames(User user) {
        user = userDao.getUserbyId(user.getUserId());

        List<Game> games = getGames();
        List<Game> playedGames = getReviewedGames(user);

        games.removeAll(playedGames);

        return games;
    }
}
