package net.therap.service.tracking;

import net.therap.dao.TrackUserDao;
import net.therap.dao.UserDao;
import net.therap.domain.GameReview;
import net.therap.domain.TrackedUser;
import net.therap.domain.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * user: pritom
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

    public void setTrackStatus(User user, User requestedUser) {
        for (TrackedUser trackedUser : user.getTrackedUsers()) {

            if (trackedUser.getTrackedUser().getEmail().equals(requestedUser.getEmail())) {
                if (trackedUser.getApproved().equals("A")) {
                    requestedUser.setApproved(true);
                }
                if (trackedUser.getApproved().equals("R")) {
                    requestedUser.setRequested(true);
                }
                if (trackedUser.getApproved().equals("U")) {
                    requestedUser.setRejected(true);
                }
            }
        }
    }

    public void addRequest(User user, User requestedUser) {

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

        for (User trackedUser : approvedUsers) {

            List<GameReview> gameReviewList = trackedUser.getPlayedGames();

            Collections.sort(gameReviewList);
        }

        return approvedUsers;
    }

    public List<User> getRequestingUsers(User user) {

        user = userDao.getUserbyId(user.getUserId());


        List<TrackedUser> trackedUsers = user.getRequestingUsers();

        List<User> requestingUsers = new ArrayList<User>();

        for (TrackedUser trackedUser : trackedUsers) {
            User approvedUser = trackedUser.getUser();

            if (trackedUser.getApproved().equals("R")) {
                requestingUsers.add(approvedUser);
            }
        }

        return requestingUsers;
    }

    public void approveUsers(String[] processedRequestingUsers, User approvingUser) {

        //List<user> approvedRequestingUsers = new ArrayList<user>();

        approvingUser = userDao.getUserbyId(approvingUser.getUserId());

        List<TrackedUser> requestingUsers = approvingUser.getRequestingUsers();

        for (String approvedUser : processedRequestingUsers) {
            User approvedRequestingUser = userDao.getUserbyId(Integer.parseInt(approvedUser));

            for (TrackedUser trackedUser : requestingUsers) {
                if (trackedUser.getUser().getEmail().equals(approvedRequestingUser.getEmail())) {
                    trackedUser.setApproved("A");
                    trackUserDao.saveRequest(trackedUser);
                    break;
                }

            }

        }


    }

    public void rejectUsers(String[] processedRequestingUsers, User approvingUser) {

        //List<user> approvedRequestingUsers = new ArrayList<user>();

        approvingUser = userDao.getUserbyId(approvingUser.getUserId());

        List<TrackedUser> requestingUsers = approvingUser.getRequestingUsers();

        for (String approvedUser : processedRequestingUsers) {
            User approvedRequestingUser = userDao.getUserbyId(Integer.parseInt(approvedUser));

            for (TrackedUser trackedUser : requestingUsers) {
                if (trackedUser.getUser().getEmail().equals(approvedRequestingUser.getEmail())) {
                    trackedUser.setApproved("U");
                    trackUserDao.saveRequest(trackedUser);
                    break;
                }

            }

        }


    }


}
