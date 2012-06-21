package net.therap.service.game;

import net.therap.dao.GameDao;
import net.therap.dao.UserDao;
import net.therap.domain.*;
import net.therap.utility.GenreMap;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * user: pritom
 * Date: 6/12/12
 * Time: 10:15 AM
 * To change this template use File | Settings | File Templates.
 */
public class GameRecommendationServiceImpl implements GameRecommendationService {

    private static final int genreScoreWeight = 40;
    private static final int ratingScoreWeight = 35;
    private static final int difficultyScoreWeight = 15;
    private static final int lengthScoreWeight = 10;
    private static final int maxRating = 500;
    private static final int maxDifficulty = 25;
    private static final int maxLength = 100;

    protected final Logger logger = Logger.getLogger(this.getClass());


    private UserDao userDao;
    private GameDao gameDao;
    private GenreMap genreMap;

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

    public GenreMap getGenreMap() {
        return genreMap;
    }

    public void setGenreMap(GenreMap genreMap) {
        this.genreMap = genreMap;
    }

    public List<Game> getRecommendations(User user) {

        user = userDao.getUserbyId(user.getUserId());

        UserGenreHistory userGenreHistory = user.getUserGenreHistory();

        UserRatingHistory userRatingHistory = user.getUserRatingHistory();

        List<Game> games = gameDao.getGames();
        List<GameReview> playedGames = user.getPlayedGames();
        List<Game> unPlayedGames = new ArrayList<Game>(games);

        for (GameReview gameReview : playedGames) {

            unPlayedGames.remove(gameReview.getGame());

        }


        Map<Float, Game> RecommendedGames = new TreeMap<Float, Game>();

        for (Game game : unPlayedGames) {

            float recommendationScore = calculateRecommendationScore(userGenreHistory, userRatingHistory, game);
            if (recommendationScore != 0) {
                RecommendedGames.put(recommendationScore, game);
            }
        }

        for (Float score : RecommendedGames.keySet()) {
            logger.info("game: " + RecommendedGames.get(score).getGameName() + "  Score: " + score);
        }

        List<Game> recommendedGames = new ArrayList(RecommendedGames.values());

        Collections.reverse(recommendedGames);

        int subListSize = Math.min(5, recommendedGames.size());
        logger.info("Sublistsize: " + subListSize);

        recommendedGames = recommendedGames.subList(0, subListSize);

        setGenre(recommendedGames);


        return recommendedGames;
    }

    public float calculateRecommendationScore(UserGenreHistory userGenreHistory, UserRatingHistory userRatingHistory, Game game) {
        float genreScore = calculateGenreScore(userGenreHistory, game);

        float ratingScore = calculateRatingScore(userRatingHistory, game);

        float difficultyScore = calculateDifficultyScore(userRatingHistory, game);

        float gameLengthScore = calculateLengthScore(userRatingHistory, game);

        float totalScore = genreScore + ratingScore + difficultyScore + gameLengthScore;


        return totalScore;
    }

    public float calculateGenreScore(UserGenreHistory userGenreHistory, Game game) {

        int userGenreSum = userGenreHistory.getFpsGenreCount() + userGenreHistory.getTpsGenreCount() + userGenreHistory.getActionGenreCount() + userGenreHistory.getAdventureGenreCount() + userGenreHistory.getSandboxGenreCount() + userGenreHistory.getRpgGenreCount() + userGenreHistory.getFightingGenreCount() + userGenreHistory.getHacknslashGenreCount() + userGenreHistory.getHorrorGenreCount() + userGenreHistory.getMmoGenreCount() + userGenreHistory.getPlatformerGenreCount() + userGenreHistory.getPuzzleGenreCount() + userGenreHistory.getRacingGenreCount() + userGenreHistory.getRtsGenreCount() + userGenreHistory.getSimulationGenreCount() + userGenreHistory.getSportsGenreCount() + userGenreHistory.getStealthGenreCount();

        if (userGenreSum == 0) {
            return 0;
        }

        float[] genreWeights = new float[17];

        genreWeights[0] = (float) userGenreHistory.getFpsGenreCount() / (float) userGenreSum;
        genreWeights[1] = (float) userGenreHistory.getTpsGenreCount() / (float) userGenreSum;
        genreWeights[2] = (float) userGenreHistory.getActionGenreCount() / (float) userGenreSum;
        genreWeights[3] = (float) userGenreHistory.getAdventureGenreCount() / (float) userGenreSum;
        genreWeights[4] = (float) userGenreHistory.getSandboxGenreCount() / (float) userGenreSum;
        genreWeights[5] = (float) userGenreHistory.getRpgGenreCount() / (float) userGenreSum;
        genreWeights[6] = (float) userGenreHistory.getRtsGenreCount() / (float) userGenreSum;
        genreWeights[7] = (float) userGenreHistory.getHorrorGenreCount() / (float) userGenreSum;
        genreWeights[8] = (float) userGenreHistory.getHacknslashGenreCount() / (float) userGenreSum;
        genreWeights[9] = (float) userGenreHistory.getStealthGenreCount() / (float) userGenreSum;
        genreWeights[10] = (float) userGenreHistory.getSimulationGenreCount() / (float) userGenreSum;
        genreWeights[11] = (float) userGenreHistory.getSportsGenreCount() / (float) userGenreSum;
        genreWeights[12] = (float) userGenreHistory.getRacingGenreCount() / (float) userGenreSum;
        genreWeights[13] = (float) userGenreHistory.getFightingGenreCount() / (float) userGenreSum;
        genreWeights[14] = (float) userGenreHistory.getMmoGenreCount() / (float) userGenreSum;
        genreWeights[15] = (float) userGenreHistory.getPuzzleGenreCount() / (float) userGenreSum;
        genreWeights[16] = (float) userGenreHistory.getPlatformerGenreCount() / (float) userGenreSum;

        float genreScore = 0;

        int gameGenre = game.getGenre();

        int index = 16;

        for (int mask : (Set<Integer>) genreMap.getGenreMap().keySet()) {

            if ((gameGenre & mask) != 0) {
                genreScore += genreWeights[index];
                logger.info("game: " + game.getGameName() + " Index " + index + " " + genreMap.getGenreMap().get(mask) + " : " + genreWeights[index]);
            }
            index--;
        }
        genreScore = genreScore * genreScoreWeight;

        logger.info("game: " + game.getGameName() + "Genre Score: " + genreScore);

        return genreScore;
    }

    public float calculateRatingScore(UserRatingHistory userRatingHistory, Game game) {


        float ratingScore = 0;


        float presentationScore = userRatingHistory.getAverageRatingPresentation() * game.getRatingPresentation();
        float graphicsScore = userRatingHistory.getAverageRatingGraphics() * game.getRatingGraphics();
        float gameplayScore = userRatingHistory.getAverageRatingGamePlay() * game.getRatingGamePlay();
        float soundScore = userRatingHistory.getAverageRatingSound() * game.getRatingSound();
        float longevityScore = userRatingHistory.getAverageRatingLongevity() * game.getRatingLongevity();

        ratingScore = presentationScore + graphicsScore + gameplayScore + soundScore + longevityScore;

        ratingScore = (ratingScore * ratingScoreWeight) / maxRating;

        logger.info("game: " + game.getGameName() + "Rating Score: " + ratingScore);

        return ratingScore;


    }

    public float calculateDifficultyScore(UserRatingHistory userRatingHistory, Game game) {

        if (userRatingHistory.getAverageRatingDifficulty() == 0f) {
            return 0;
        }


        float difficulty = (userRatingHistory.getAverageRatingDifficulty() - game.getDifficulty()) * (userRatingHistory.getAverageRatingDifficulty() - game.getDifficulty());

        float difficultyScore = ((maxDifficulty - difficulty) * difficultyScoreWeight) / maxDifficulty;

        logger.info("game: " + game.getGameName() + "Difficulty Score: " + difficultyScore);

        return difficultyScore;
    }

    public float calculateLengthScore(UserRatingHistory userRatingHistory, Game game) {
        if (userRatingHistory.getAverageRatingGameLength() == 0) {
            return 0;
        }

        float length = maxLength - Math.abs(userRatingHistory.getAverageRatingGameLength() - game.getGameLength());

        if (length < 0) {
            return 0;
        }

        length = (length * lengthScoreWeight) / maxLength;

        logger.info("game: " + game.getGameName() + "Length Score: " + length);

        return length;
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