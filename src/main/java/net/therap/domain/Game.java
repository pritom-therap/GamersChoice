package net.therap.domain;

import javax.persistence.*;
import java.sql.Blob;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
<<<<<<< HEAD
 * user: pritom
=======
 * User: pritom
>>>>>>> 958d29b8c056bfe7629bdb3f882003e9797e4933
 * Date: 5/30/12
 * Time: 2:46 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "PT_GAME")
public class Game {

    private int gameId;
    private String gameName;
    private Blob screenShot;
    private String platform;
    private int difficulty;
    private Date releaseDate;
    private String synopsis;
    private float ratingPresentation;
    private float ratingGraphics;
    private float ratingSound;
    private float ratingGamePlay;
    private float ratingLongevity;
    private float ratingOverall;
    private float gameLength;
    private int voteCount;
    private int genre;
    private String developer;
    private String genreString;
    private long version;
    private String status;
    private List<GameReview> reviews;
    private List<User> trackingUsers;
    private boolean played;
    private boolean tracked;



    @OneToMany(mappedBy = "gameReviewId.game")
    public List<GameReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<GameReview> reviews) {
        this.reviews = reviews;
    }

    @Id
    @SequenceGenerator(name = "PT_GAME_SEQ", sequenceName = "PT_GAME_SEQ")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "PT_GAME_SEQ")
    @Column(name = "GAME_ID")
    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    @Column(name = "GAME_NAME")
    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    @Column(name = "SCREENSHOT")
    @Lob
    public Blob getScreenShot() {
        return screenShot;
    }

    public void setScreenShot(Blob screenShot) {
        this.screenShot = screenShot;
    }

    @Column(name = "PLATFORM")
    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Column(name = "DIFFICULTY")
    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "RELEASE_DATE")
    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Column(name = "SYNOPSIS")
    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    @Column(name = "RATING_PRESENTATION")
    public float getRatingPresentation() {
        return ratingPresentation;
    }

    public void setRatingPresentation(float ratingPresentation) {
        this.ratingPresentation = ratingPresentation;
    }

    @Column(name = "RATING_GRAPHICS")
    public float getRatingGraphics() {
        return ratingGraphics;
    }

    public void setRatingGraphics(float ratingGraphics) {
        this.ratingGraphics = ratingGraphics;
    }

    @Column(name = "RATING_SOUND")
    public float getRatingSound() {
        return ratingSound;
    }

    public void setRatingSound(float ratingSound) {
        this.ratingSound = ratingSound;
    }

    @Column(name = "RATING_GAMEPLAY")
    public float getRatingGamePlay() {
        return ratingGamePlay;
    }

    public void setRatingGamePlay(float ratingGamePlay) {
        this.ratingGamePlay = ratingGamePlay;
    }

    @Column(name = "RATING_LONGEVITY")
    public float getRatingLongevity() {
        return ratingLongevity;
    }

    public void setRatingLongevity(float ratingLongevity) {
        this.ratingLongevity = ratingLongevity;
    }

    @Column(name = "GAME_LENGTH")
    public float getGameLength() {
        return gameLength;
    }

    public void setGameLength(float gameLength) {
        this.gameLength = gameLength;
    }

    @Column(name = "VOTE_COUNT")
    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    @Column(name = "GENRE")
    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }
    @Version
    @Column(name = "VERSION")
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @ManyToMany(mappedBy = "trackedGames")
    public List<User> getTrackingUsers() {
        return trackingUsers;
    }

    public void setTrackingUsers(List<User> trackingUsers) {
        this.trackingUsers = trackingUsers;
    }

    @Transient
    public String getGenreString() {
        return genreString;
    }

    public void setGenreString(String genreString) {
        this.genreString = genreString;
    }

    @Column(name = "DEVELOPER")
    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    @Transient

    public float getRatingOverall() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        this.ratingOverall = Float.parseFloat(decimalFormat.format((ratingPresentation+ratingGraphics+ratingGamePlay+ratingSound+ratingLongevity)/5));

        return ratingOverall;
    }

    public void setRatingOverall(float ratingOverall) {
        this.ratingOverall = ratingOverall;
    }

    @Transient
    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }

    @Transient
    public boolean isTracked() {
        return tracked;
    }

    public void setTracked(boolean tracked) {
        this.tracked = tracked;
    }
}
