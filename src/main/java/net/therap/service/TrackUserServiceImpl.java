package net.therap.service;

import net.therap.dao.TrackUserDao;
import net.therap.dao.UserDao;
import net.therap.domain.TrackedUser;
import net.therap.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: pritom
 * Date: 6/13/12
 * Time: 10:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class TrackUserServiceImpl implements TrackUserService {

    UserDao userDao;
    TrackUserDao trackUserDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public TrackUserDao getTrackUserDao() {
        return trackUserDao;
    }

    public void setTrackUserDao(TrackUserDao trackUserDao) {
        this.trackUserDao = trackUserDao;
    }

    public boolean checkIfTracked(User user, User trackedUser) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void sendRequest(User user, User requestedUser) {

        TrackedUser trackedUser = new TrackedUser();

        user = userDao.getUserbyId(user.getUserId());
        requestedUser = userDao.getUserbyId(requestedUser.getUserId());


        trackedUser.setUser(user);
        trackedUser.setTrackedUser(requestedUser);

        trackedUser.setApproved("R");

        trackUserDao.saveRequest(trackedUser);
    }

    public List<User> getApprovedUsers(User user) {

        user = userDao.getUserbyId(user.getUserId());


        List<TrackedUser> trackedUsers = user.getTrackedUsers();

        List<User> approvedUsers = new ArrayList<User>();

        for (TrackedUser trackedUser : trackedUsers) {
            User approvedUser = trackedUser.getTrackedUser();

            if (trackedUser.getApproved().equals("A")) {
                approvedUsers.add(approvedUser);
            }
        }

        return  approvedUsers;
    }
}
