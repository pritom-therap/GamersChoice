package net.therap.dao;

import net.therap.domain.Game;
import net.therap.domain.GameReview;
import net.therap.domain.User;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * user: pritom
 * Date: 6/4/12
 * Time: 11:25 AM
 * To change this template use File | Settings | File Templates.
 */
public interface UserDao {


    final int fpsGenreMask = 65536;
    final int tpsGenreMask = 32768;
    final int actionGenreMask = 16384;
    final int adventureGenreMask = 8192;
    final int sandboxGenreMask = 4096;
    final int rpgGenreMask = 2048;
    final int rtsGenreMask = 1024;
    final int horrorGenreMask = 512;
    final int hacknslashGenreMask = 256;
    final int stealthGenreMask = 128;
    final int simulationGenreMask = 64;
    final int sportsGenreMask = 32;
    final int racingGenreMask = 16;
    final int fightingGenreMask = 8;
    final int mmoGenreMask = 4;
    final int puzzleGenreMask = 2;
    final int platformerGenreMask = 1;

    public User getUserbyId(int id);

    public List<User> getUsers();

    public void updateUserGenreHistory(GameReview gameReview);

    public void updateUserRatingHistory(GameReview gameReview);

    public void saveUser(User user);

    public void updateUser(User user);

    public void deleteTrackedGameAfterReview(User user, Game game);


}
