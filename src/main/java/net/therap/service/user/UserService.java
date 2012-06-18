package net.therap.service.user;

import net.therap.command.UserCmd;
import net.therap.command.LoginCmd;
import net.therap.domain.User;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * user: pritom
 * Date: 6/7/12
 * Time: 2:06 PM
 * To change this template use File | Settings | File Templates.
 */
public interface UserService {

    public User getUserById(int id);

    public void saveUser(UserCmd userCmd);

    public User Authenticate(LoginCmd loginCmd);

    public List<User> getUsers();

}
