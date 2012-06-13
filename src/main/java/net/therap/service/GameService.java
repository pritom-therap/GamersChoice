package net.therap.service;

import net.therap.command.AddGame;
import net.therap.domain.Game;

/**
 * Created by IntelliJ IDEA.
 * User: pritom
 * Date: 6/5/12
 * Time: 5:32 PM
 * To change this template use File | Settings | File Templates.
 */
public interface GameService {


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

    public int addGame(AddGame addGame);

     public String getGenreAsString(int genre);

    public Game getGame(int gameId);
}
