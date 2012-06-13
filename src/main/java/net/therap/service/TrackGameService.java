package net.therap.service;

import net.therap.domain.Game;
import net.therap.domain.User;

/**
 * Created by IntelliJ IDEA.
 * User: pritom
 * Date: 6/10/12
 * Time: 11:41 AM
 * To change this template use File | Settings | File Templates.
 */
public interface TrackGameService {

    public User addNewTrackedGame(Game game, User user);
}
