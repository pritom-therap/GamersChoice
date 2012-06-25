package net.therap.service.game;

import net.therap.domain.Game;
import net.therap.domain.User;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * user: pritom
 * Date: 6/12/12
 * Time: 10:13 AM
 * To change this template use File | Settings | File Templates.
 */
public interface GameRecommendationService {

    public List<Game> getRecommendations(User user);
}
