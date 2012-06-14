package net.therap.service;

import net.therap.command.AddUser;
import net.therap.command.Login;
import net.therap.dao.UserDao;
import net.therap.domain.User;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: pritom
 * Date: 6/7/12
 * Time: 2:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserServiceImpl implements UserService {

    UserDao userDao;
    Logger logger = Logger.getLogger(this.getClass());

    public void saveUser(AddUser addUser) {
        User user = new User();

        logger.info(addUser.getPassword());

        user.setUserName(addUser.getUserName());
        user.setEmail(addUser.getEmail());
        user.setPassword(addUser.getPassword());
        user.setJoinDate(new Date());
        user.setStatus("U");
        user.setPlayedGameCount(0);

        userDao.saveUser(user);


    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public User Authenticate(Login login) {

        List<User> users = userDao.getUsers();



        for (User user : users) {
            logger.info("Login email: " + login.getEmail() + " User email: " + user.getEmail() + " Login password: " + login.getPassword() + " User password: " + user.getPassword());
            if (user.getEmail().equals(login.getEmail()) && user.getPassword().equals(login.getPassword())) {
                return user;
            }


        }

        return null;

    }
}
