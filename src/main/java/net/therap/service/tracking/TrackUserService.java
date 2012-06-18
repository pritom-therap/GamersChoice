package net.therap.service.tracking;

import net.therap.domain.User;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * user: pritom
 * Date: 6/13/12
 * Time: 10:07 AM
 * To change this template use File | Settings | File Templates.
 */
public interface TrackUserService {

    public void setTrackStatus(User user, User requestedUser);

    public void addRequest(User user, User requestedUser);

    public List<User> getApprovedUsers(User user);

    public List<User> getRequestingUsers(User user);

    public void approveUsers(String [] processedRequestingUsers, User approvingUser);

    public void rejectUsers(String[] processedRequestingUsers, User approvingUser);
}
