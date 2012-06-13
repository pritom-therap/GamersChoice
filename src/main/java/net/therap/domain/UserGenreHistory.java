package net.therap.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: pritom
 * Date: 5/30/12
 * Time: 2:38 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "PT_USER_GENRE_HISTORY")
public class UserGenreHistory {

    int userId;
    int fpsGenreCount;
    int tpsGenreCount;
    int actionGenreCount;
    int adventureGenreCount;
    int sandboxGenreCount;
    int rpgGenreCount;
    int rtsGenreCount;
    int horrorGenreCount;
    int hacknslashGenreCount;
    int stealthGenreCount;
    int simulationGenreCount;
    int sportsGenreCount;
    int racingGenreCount;
    int fightingGenreCount;
    int mmoGenreCount;
    int puzzleGenreCount;
    int platformerGenreCount;
    long version;
    User user;

    @GenericGenerator(name = "generator", strategy = "foreign",	parameters = @Parameter(name = "property", value = "user"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "USER_ID")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @OneToOne
    @PrimaryKeyJoinColumn
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "FPS_GENRE_COUNT")
    public int getFpsGenreCount() {
        return fpsGenreCount;
    }

    public void setFpsGenreCount(int fpsGenreCount) {
        this.fpsGenreCount = fpsGenreCount;
    }

    @Column(name = "TPS_GENRE_COUNT")
    public int getTpsGenreCount() {
        return tpsGenreCount;
    }

    public void setTpsGenreCount(int tpsGenreCount) {
        this.tpsGenreCount = tpsGenreCount;
    }

    @Column(name = "ACTION_GENRE_COUNT")
    public int getActionGenreCount() {
        return actionGenreCount;
    }

    public void setActionGenreCount(int actionGenreCount) {
        this.actionGenreCount = actionGenreCount;
    }

    @Column(name = "ADVENTURE_GENRE_COUNT")
    public int getAdventureGenreCount() {
        return adventureGenreCount;
    }

    public void setAdventureGenreCount(int adventureGenreCount) {
        this.adventureGenreCount = adventureGenreCount;
    }

    @Column(name = "SANDBOX_GENRE_COUNT")
    public int getSandboxGenreCount() {
        return sandboxGenreCount;
    }

    public void setSandboxGenreCount(int sandboxGenreCount) {
        this.sandboxGenreCount = sandboxGenreCount;
    }

    @Column(name = "RPG_GENRE_COUNT")
    public int getRpgGenreCount() {
        return rpgGenreCount;
    }

    public void setRpgGenreCount(int rpgGenreCount) {
        this.rpgGenreCount = rpgGenreCount;
    }

    @Column(name = "RTS_GENRE_COUNT")
    public int getRtsGenreCount() {
        return rtsGenreCount;
    }

    public void setRtsGenreCount(int rtsGenreCount) {
        this.rtsGenreCount = rtsGenreCount;
    }

    @Column(name = "HORROR_GENRE_COUNT")
    public int getHorrorGenreCount() {
        return horrorGenreCount;
    }

    public void setHorrorGenreCount(int horrorGenreCount) {
        this.horrorGenreCount = horrorGenreCount;
    }

    @Column(name = "HACKNSLASH_GENRE_COUNT")
    public int getHacknslashGenreCount() {
        return hacknslashGenreCount;
    }

    public void setHacknslashGenreCount(int hacknslashGenreCount) {
        this.hacknslashGenreCount = hacknslashGenreCount;
    }


    @Column(name = "STEALTH_GENRE_COUNT")
    public int getStealthGenreCount() {
        return stealthGenreCount;
    }

    public void setStealthGenreCount(int stealthGenreCount) {
        this.stealthGenreCount = stealthGenreCount;
    }

    @Column(name = "SIMULATION_GENRE_COUNT")
    public int getSimulationGenreCount() {
        return simulationGenreCount;
    }

    public void setSimulationGenreCount(int simulationGenreCount) {
        this.simulationGenreCount = simulationGenreCount;
    }

    @Column(name = "SPORTS_GENRE_COUNT")
    public int getSportsGenreCount() {
        return sportsGenreCount;
    }

    public void setSportsGenreCount(int sportsGenreCount) {
        this.sportsGenreCount = sportsGenreCount;
    }

    @Column(name = "RACING_GENRE_COUNT")
    public int getRacingGenreCount() {
        return racingGenreCount;
    }

    public void setRacingGenreCount(int racingGenreCount) {
        this.racingGenreCount = racingGenreCount;
    }

    @Column(name = "FIGHTING_GENRE_COUNT")
    public int getFightingGenreCount() {
        return fightingGenreCount;
    }

    public void setFightingGenreCount(int fightingGenreCount) {
        this.fightingGenreCount = fightingGenreCount;
    }

    @Column(name = "MMO_GENRE_COUNT")
    public int getMmoGenreCount() {
        return mmoGenreCount;
    }

    public void setMmoGenreCount(int mmoGenreCount) {
        this.mmoGenreCount = mmoGenreCount;
    }

    @Column(name = "PUZZLE_GENRE_COUNT")
    public int getPuzzleGenreCount() {
        return puzzleGenreCount;
    }

    public void setPuzzleGenreCount(int puzzleGenreCount) {
        this.puzzleGenreCount = puzzleGenreCount;
    }

    @Column(name = "PLATFORMER_GENRE_COUNT")
    public int getPlatformerGenreCount() {
        return platformerGenreCount;
    }

    public void setPlatformerGenreCount(int platformerGenreCount) {
        this.platformerGenreCount = platformerGenreCount;
    }

    @Version
    @Column(name = "VERSION")
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
