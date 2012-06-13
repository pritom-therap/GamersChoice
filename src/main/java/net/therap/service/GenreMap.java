package net.therap.service;

import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.bidimap.TreeBidiMap;

/**
 * Created by IntelliJ IDEA.
 * User: pritom
 * Date: 6/6/12
 * Time: 2:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class GenreMap {

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

    BidiMap genreMap;

    public GenreMap(){

        genreMap = new TreeBidiMap();

        genreMap.put(fpsGenreMask, "FPS");
        genreMap.put(tpsGenreMask, "TPS");
        genreMap.put(actionGenreMask, "Action");
        genreMap.put(adventureGenreMask, "Adventure");
        genreMap.put(sandboxGenreMask, "Sandbox");
        genreMap.put(rpgGenreMask, "RPG");
        genreMap.put(rtsGenreMask, "RTS");
        genreMap.put(horrorGenreMask, "Horror");
        genreMap.put(hacknslashGenreMask, "Hacknslash");
        genreMap.put(stealthGenreMask, "Stealth");
        genreMap.put(simulationGenreMask, "Simulation");
        genreMap.put(sportsGenreMask, "Sports");
        genreMap.put(racingGenreMask, "Racing");
        genreMap.put(fightingGenreMask, "Fighting");
        genreMap.put(mmoGenreMask, "MMO");
        genreMap.put(puzzleGenreMask, "Puzzle");
        genreMap.put(platformerGenreMask, "Platformer");

    }


    public BidiMap getGenreMap() {
        return genreMap;
    }

    public void setGenreMap(BidiMap genreMap) {
        this.genreMap = genreMap;
    }
}
