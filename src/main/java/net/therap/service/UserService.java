package net.therap.service;

import net.therap.command.AddUser;
import net.therap.command.Login;
import net.therap.domain.User;

/**
 * Created by IntelliJ IDEA.
 * User: pritom
 * Date: 6/7/12
 * Time: 2:06 PM
 * To change this template use File | Settings | File Templates.
 */
public interface UserService {

    public void saveUser(AddUser addUser);

    public User Authenticate(Login login);
}
