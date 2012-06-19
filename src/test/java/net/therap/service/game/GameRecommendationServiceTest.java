package net.therap.service.game;

import net.therap.dao.GameDao;
import net.therap.dao.UserDao;
import net.therap.domain.*;
import net.therap.utility.GenreMap;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.unitils.UnitilsTestNG;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: pritom
 * Date: 6/18/12
 * Time: 5:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameRecommendationServiceTest extends UnitilsTestNG {

    @TestedObject
    private GameRecommendationServiceImpl gameRecommendationServiceImpl;

    @InjectIntoByType
    private Mock<GameDao> mockGameDao;
    @InjectIntoByType
    private Mock<UserDao> mockUserDao;

    @InjectIntoByType
    protected GenreMap genreMap;


    protected Game game1, game2, game3;
    protected User user;


    protected UserRatingHistory userRatingHistory;
    protected UserGenreHistory userGenreHistory;

    @BeforeMethod
    public void setUp() {
        game1 = new Game();
        game2 = new Game();
        game3 = new Game();
        userRatingHistory = new UserRatingHistory();
        userGenreHistory = new UserGenreHistory();
        user = new User();
    }


    @Test
    public void testCalculateDifficultyScore() throws Exception {

        game1.setDifficulty(2);
        userRatingHistory.setAverageRatingDifficulty(0);

        Assert.assertEquals(0f, gameRecommendationServiceImpl.calculateDifficultyScore(userRatingHistory, game1));

        game1.setDifficulty(0);
        userRatingHistory.setAverageRatingDifficulty(5);

        Assert.assertEquals(0f, gameRecommendationServiceImpl.calculateDifficultyScore(userRatingHistory, game1));


        game1.setDifficulty(2);
        userRatingHistory.setAverageRatingDifficulty(2.0f);


        Assert.assertEquals(15f, gameRecommendationServiceImpl.calculateDifficultyScore(userRatingHistory, game1));

        game1.setDifficulty(4);
        userRatingHistory.setAverageRatingDifficulty(2f);

        Assert.assertEquals(12.6f, gameRecommendationServiceImpl.calculateDifficultyScore(userRatingHistory, game1));
    }

    @Test
    public void testCalculateLengthScore() throws Exception {

        game1.setGameLength(20);
        userRatingHistory.setAverageRatingGameLength(0.0f);

        Assert.assertEquals(0f, gameRecommendationServiceImpl.calculateLengthScore(userRatingHistory, game1));

        game1.setGameLength(15);
        userRatingHistory.setAverageRatingGameLength(125.0f);

        Assert.assertEquals(0f, gameRecommendationServiceImpl.calculateLengthScore(userRatingHistory, game1));

        game1.setGameLength(10);
        userRatingHistory.setAverageRatingGameLength(10f);

        Assert.assertEquals(10f, gameRecommendationServiceImpl.calculateLengthScore(userRatingHistory, game1));

        game1.setGameLength(25);
        userRatingHistory.setAverageRatingGameLength(35);

        Assert.assertEquals(9f, gameRecommendationServiceImpl.calculateLengthScore(userRatingHistory, game1));

    }


    @Test
    public void testCalculateRatingScore() throws Exception {


        game1.setRatingGamePlay(0);
        game1.setRatingGraphics(0);
        game1.setRatingLongevity(0);
        game1.setRatingPresentation(0);
        game1.setRatingSound(0);

        userRatingHistory.setAverageRatingGamePlay(7.5f);
        userRatingHistory.setAverageRatingGraphics(8.5f);
        userRatingHistory.setAverageRatingPresentation(6.5f);
        userRatingHistory.setAverageRatingLongevity(7.5f);
        userRatingHistory.setAverageRatingSound(5.0F);

        Assert.assertEquals(0f, gameRecommendationServiceImpl.calculateRatingScore(userRatingHistory, game1));

        game1.setRatingGamePlay(10);
        game1.setRatingGraphics(10);
        game1.setRatingLongevity(10);
        game1.setRatingPresentation(10);
        game1.setRatingSound(10);

        userRatingHistory.setAverageRatingGamePlay(10f);
        userRatingHistory.setAverageRatingGraphics(10f);
        userRatingHistory.setAverageRatingPresentation(10f);
        userRatingHistory.setAverageRatingLongevity(10f);
        userRatingHistory.setAverageRatingSound(10F);

        Assert.assertEquals(35f, gameRecommendationServiceImpl.calculateRatingScore(userRatingHistory, game1));
    }


    @Test
    public void testCalculateGenreScore() throws Exception {
        genreMap = new GenreMap();
        gameRecommendationServiceImpl.setGenreMap(genreMap);

        userGenreHistory.setFpsGenreCount(0);
        userGenreHistory.setTpsGenreCount(0);
        userGenreHistory.setActionGenreCount(0);
        userGenreHistory.setAdventureGenreCount(0);
        userGenreHistory.setSandboxGenreCount(0);
        userGenreHistory.setRpgGenreCount(0);
        userGenreHistory.setRtsGenreCount(0);
        userGenreHistory.setHacknslashGenreCount(0);
        userGenreHistory.setHorrorGenreCount(0);
        userGenreHistory.setStealthGenreCount(0);
        userGenreHistory.setSimulationGenreCount(0);
        userGenreHistory.setSportsGenreCount(0);
        userGenreHistory.setRacingGenreCount(0);
        userGenreHistory.setFightingGenreCount(0);
        userGenreHistory.setMmoGenreCount(0);
        userGenreHistory.setPuzzleGenreCount(0);
        userGenreHistory.setPlatformerGenreCount(0);

        game1.setGenre(1);

        Assert.assertEquals(0f, gameRecommendationServiceImpl.calculateGenreScore(userGenreHistory, game1));

        userGenreHistory.setFpsGenreCount(1);
        userGenreHistory.setTpsGenreCount(0);
        userGenreHistory.setActionGenreCount(0);
        userGenreHistory.setAdventureGenreCount(0);
        userGenreHistory.setSandboxGenreCount(0);
        userGenreHistory.setRpgGenreCount(0);
        userGenreHistory.setRtsGenreCount(0);
        userGenreHistory.setHacknslashGenreCount(0);
        userGenreHistory.setHorrorGenreCount(0);
        userGenreHistory.setStealthGenreCount(0);
        userGenreHistory.setSimulationGenreCount(0);
        userGenreHistory.setSportsGenreCount(0);
        userGenreHistory.setRacingGenreCount(0);
        userGenreHistory.setFightingGenreCount(0);
        userGenreHistory.setMmoGenreCount(0);
        userGenreHistory.setPuzzleGenreCount(0);
        userGenreHistory.setPlatformerGenreCount(0);

        game1.setGenre(0);

        Assert.assertEquals(0f, gameRecommendationServiceImpl.calculateGenreScore(userGenreHistory, game1));

        userGenreHistory.setFpsGenreCount(8);
        userGenreHistory.setTpsGenreCount(0);
        userGenreHistory.setActionGenreCount(0);
        userGenreHistory.setAdventureGenreCount(0);
        userGenreHistory.setSandboxGenreCount(0);
        userGenreHistory.setRpgGenreCount(0);
        userGenreHistory.setRtsGenreCount(0);
        userGenreHistory.setHacknslashGenreCount(0);
        userGenreHistory.setHorrorGenreCount(0);
        userGenreHistory.setStealthGenreCount(0);
        userGenreHistory.setSimulationGenreCount(0);
        userGenreHistory.setSportsGenreCount(0);
        userGenreHistory.setRacingGenreCount(0);
        userGenreHistory.setFightingGenreCount(0);
        userGenreHistory.setMmoGenreCount(0);
        userGenreHistory.setPuzzleGenreCount(0);
        userGenreHistory.setPlatformerGenreCount(0);

        game1.setGenre(65536);

        Assert.assertEquals(40f, gameRecommendationServiceImpl.calculateGenreScore(userGenreHistory, game1));

        userGenreHistory.setFpsGenreCount(0);
        userGenreHistory.setTpsGenreCount(8);
        userGenreHistory.setActionGenreCount(0);
        userGenreHistory.setAdventureGenreCount(0);
        userGenreHistory.setSandboxGenreCount(0);
        userGenreHistory.setRpgGenreCount(0);
        userGenreHistory.setRtsGenreCount(0);
        userGenreHistory.setHacknslashGenreCount(0);
        userGenreHistory.setHorrorGenreCount(0);
        userGenreHistory.setStealthGenreCount(0);
        userGenreHistory.setSimulationGenreCount(0);
        userGenreHistory.setSportsGenreCount(0);
        userGenreHistory.setRacingGenreCount(0);
        userGenreHistory.setFightingGenreCount(0);
        userGenreHistory.setMmoGenreCount(0);
        userGenreHistory.setPuzzleGenreCount(0);
        userGenreHistory.setPlatformerGenreCount(0);

        game1.setGenre(65536);

        Assert.assertEquals(0f, gameRecommendationServiceImpl.calculateGenreScore(userGenreHistory, game1));

    }


    @Test
    public  void  testGetRecommendation() throws Exception{

        game1.setGenre(65536);
        game1.setDifficulty(2);
        game1.setGameLength(8);
        game1.setRatingGraphics(8);
        game1.setRatingGamePlay(7);
        game1.setRatingPresentation(10);
        game1.setRatingSound(7);
        game1.setRatingLongevity(4);


        game2.setGenre(67584);
        game2.setDifficulty(4);
        game2.setGameLength(15);
        game2.setRatingGraphics(8);
        game2.setRatingGamePlay(7);
        game2.setRatingPresentation(9);
        game2.setRatingSound(6);
        game2.setRatingLongevity(5);

        game3.setGenre(256);
        game3.setDifficulty(3);
        game3.setGameLength(22);
        game3.setRatingGraphics(7);
        game3.setRatingGamePlay(9);
        game3.setRatingPresentation(8);
        game3.setRatingSound(9);
        game3.setRatingLongevity(5);


        userGenreHistory.setFpsGenreCount(4);
        userGenreHistory.setTpsGenreCount(0);
        userGenreHistory.setActionGenreCount(0);
        userGenreHistory.setAdventureGenreCount(0);
        userGenreHistory.setSandboxGenreCount(0);
        userGenreHistory.setRpgGenreCount(5);
        userGenreHistory.setRtsGenreCount(0);
        userGenreHistory.setHacknslashGenreCount(1);
        userGenreHistory.setHorrorGenreCount(0);
        userGenreHistory.setStealthGenreCount(0);
        userGenreHistory.setSimulationGenreCount(0);
        userGenreHistory.setSportsGenreCount(0);
        userGenreHistory.setRacingGenreCount(0);
        userGenreHistory.setFightingGenreCount(0);
        userGenreHistory.setMmoGenreCount(0);
        userGenreHistory.setPuzzleGenreCount(0);
        userGenreHistory.setPlatformerGenreCount(0);


        userRatingHistory.setAverageRatingDifficulty(3);
        userRatingHistory.setAverageRatingGameLength(15);
        userRatingHistory.setAverageRatingGraphics(8);
        userRatingHistory.setAverageRatingGamePlay(7);
        userRatingHistory.setAverageRatingPresentation(9);
        userRatingHistory.setAverageRatingSound(6);
        userRatingHistory.setAverageRatingLongevity(5);

        List<GameReview> playedGames = new ArrayList<GameReview>();
        user.setPlayedGames(playedGames);


        user.setUserGenreHistory(userGenreHistory);
        user.setUserRatingHistory(userRatingHistory);
        user.setUserId(1);

        mockUserDao.returns(user).getUserbyId(user.getUserId());

        List<Game> games = Arrays.asList(game1,game2,game3);

        mockGameDao.returns(games).getGames();

        List<Game> recommendedGames = gameRecommendationServiceImpl.getRecommendations(user);

        Assert.assertEquals(game2,recommendedGames.get(0));
        Assert.assertEquals(game1,recommendedGames.get(1));
        Assert.assertEquals(game3,recommendedGames.get(2));


    }


}