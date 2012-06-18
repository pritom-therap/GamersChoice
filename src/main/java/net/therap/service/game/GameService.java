package net.therap.service.game;

import net.therap.command.GameCmd;
import net.therap.domain.Game;
import net.therap.domain.User;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * user: pritom
 * Date: 6/5/12
 * Time: 5:32 PM
 * To change this template use File | Settings | File Templates.
 */
public interface GameService {

/*
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
    final int platformerGenreMask = 1;*/

    public int addGame(GameCmd gameCmd);

    public String getGenreAsString(int genre);

    public Game getGameById(int gameId, User user);

    public Game getNewlyAddedGame(int newlyAddedGameId, GameCmd addNewGameCmd);

    public List<Game> getGames();

    public void setGenre(List<Game> games);

    public List<Game> getReviewedGames(User user);

    public List<Game> getTopGames();
}
