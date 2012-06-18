package net.therap.service.game;

import net.therap.dao.GameDao;
import net.therap.dao.UserDao;
import net.therap.domain.Game;
import net.therap.domain.UserRatingHistory;
import net.therap.utility.GenreMap;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.unitils.UnitilsTestNG;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;
import org.unitils.mock.annotation.Dummy;

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


    GenreMap genreMap;

    @Dummy
    protected Game game1, game2;

    @Dummy
    protected UserRatingHistory userRatingHistory;

    @Test
    public void  testCalculateDifficultyScore() throws Exception{

        game1.setDifficulty(2);
        userRatingHistory.setAverageRatingDifficulty(0);

        Assert.assertEquals(0f,gameRecommendationServiceImpl.calculateDifficultyScore(userRatingHistory,game1));

        game1.setDifficulty(0);
        userRatingHistory.setAverageRatingDifficulty(5);

        Assert.assertEquals(0f,gameRecommendationServiceImpl.calculateDifficultyScore(userRatingHistory,game1));

    }




    
}   
