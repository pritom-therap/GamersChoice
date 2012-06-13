package net.therap.service;

import net.therap.command.AddGame;
import net.therap.dao.GameDao;
import net.therap.domain.Game;
import org.hibernate.Hibernate;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.util.Date;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: pritom
 * Date: 6/5/12
 * Time: 5:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameServiceImpl implements GameService {
    GameDao gameDao;
    GenreMap genreMap;

    public GameDao getGameDao() {
        return gameDao;
    }

    public void setGameDao(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    public int addGame(AddGame addGame) {

        /*BidiMap genreMap = new TreeBidiMap();

        genreMap.put(fpsGenreMask, "FPS");
        genreMap.put(tpsGenreMask, "TPS");
        genreMap.put(actionGenreMask, "Action");
        genreMap.put(adventureGenreMask, "Adventure");
        genreMap.put(sandboxGenreMask, "Sandbox");
        genreMap.put(rpgGenreMask, "RPG");
        genreMap.put(rtsGenreMask, "RTS");
        genreMap.put(horrorGenreMask, "Horror");
        genreMap.put(hacknslashGenreMask, "Hacknslash");
        genreMap.put(stealthGenreMask, "Stealth");
        genreMap.put(simulationGenreMask, "Simulation");
        genreMap.put(sportsGenreMask, "Sports");
        genreMap.put(racingGenreMask, "Racing");
        genreMap.put(fightingGenreMask, "Fighting");
        genreMap.put(mmoGenreMask, "MMO");
        genreMap.put(puzzleGenreMask, "Puzzle");
        genreMap.put(platformerGenreMask, "Platformer");*/


        Game game = new Game();

        game.setGameName(addGame.getGameName());
        game.setPlatform(addGame.getPlatform());
        game.setSynopsis(addGame.getSynopsis());

        MultipartFile multipartFile = addGame.getFile();


        Blob blob = null;
        try {
            blob = Hibernate.createBlob(multipartFile.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        game.setScreenShot(blob);

        String[] genreList = addGame.getGenre();

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

        //for ()

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

    public GenreMap getGenreMap() {
        return genreMap;
    }

    public void setGenreMap(GenreMap genreMap) {
        this.genreMap = genreMap;
    }

    public Game getGame(int gameId) {

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

        return game;
    }
}
