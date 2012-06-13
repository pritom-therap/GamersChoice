package net.therap.service;

import net.therap.domain.User;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: pritom
 * Date: 6/13/12
 * Time: 10:07 AM
 * To change this template use File | Settings | File Templates.
 */
public interface TrackUserService {

    public boolean checkIfTracked(User user, User trackedUser);

    public void sendRequest(User user, User requestedUser);

    public List<User> getApprovedUsers(User user);
}
