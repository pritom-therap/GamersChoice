package net.therap.service.tracking;

import net.therap.domain.Game;
import net.therap.domain.User;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * user: pritom
 * Date: 6/10/12
 * Time: 11:41 AM
 * To change this template use File | Settings | File Templates.
 */
public interface TrackGameService {

    public User addNewTrackedGame(Game game, User user);

    public List<Game> getTrackedGamesByUser(User user);
}
