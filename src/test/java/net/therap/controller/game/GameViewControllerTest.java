package net.therap.controller.game;

import net.therap.domain.Game;
import net.therap.service.game.GameService;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.unitils.UnitilsTestNG;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;
import org.unitils.mock.annotation.Dummy;

import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: pritom
 * Date: 6/18/12
 * Time: 4:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameViewControllerTest extends UnitilsTestNG{

    @TestedObject
    private GamesViewController gamesViewController;

    @InjectIntoByType
    private Mock<GameService> mockGameService;


    protected List<Game> gameList;

    @Dummy
    protected Game game1, game2;

    @Test
    public void testHandleRequest() throws Exception{

         gameList = Arrays.asList(game1,game2);

        mockGameService.returns(gameList).getGames();

        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();

        mockHttpServletRequest.setMethod("GET");



        ModelAndView modelAndView = gamesViewController.handleRequest(mockHttpServletRequest, mockHttpServletResponse);

        Assert.assertEquals("/Game/Games",modelAndView.getViewName());

        Assert.assertEquals(gameList,modelAndView.getModelMap().get("gameList"));


    }

}
