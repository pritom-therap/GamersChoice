package net.therap.command;

/**
 * Created by IntelliJ IDEA.
 * User: pritom
 * Date: 6/3/12
 * Time: 12:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class AddReview {


    private String pros;
    private String cons;
    private int gameId;
    private float ratingPresentation;
    private float ratingGraphics;
    private float ratingSound;
    private float ratingGamePlay;
    private float ratingLongevity;
    private float gameLength;
    private int difficulty;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public float getRatingPresentation() {
        return ratingPresentation;
    }

    public void setRatingPresentation(float ratingPresentation) {
        this.ratingPresentation = ratingPresentation;
    }

    public float getRatingGraphics() {
        return ratingGraphics;
    }

    public void setRatingGraphics(float ratingGraphics) {
        this.ratingGraphics = ratingGraphics;
    }

    public float getRatingSound() {
        return ratingSound;
    }

    public void setRatingSound(float ratingSound) {
        this.ratingSound = ratingSound;
    }

    public float getRatingGamePlay() {
        return ratingGamePlay;
    }

    public void setRatingGamePlay(float ratingGamePlay) {
        this.ratingGamePlay = ratingGamePlay;
    }

    public float getRatingLongevity() {
        return ratingLongevity;
    }

    public void setRatingLongevity(float ratingLongevity) {
        this.ratingLongevity = ratingLongevity;
    }

    public float getGameLength() {
        return gameLength;
    }

    public void setGameLength(float gameLength) {
        this.gameLength = gameLength;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getCons() {

        return cons;
    }

    public void setCons(String cons) {
        this.cons = cons;
    }

    public String getPros() {
        return pros;
    }

    public void setPros(String pros) {
        this.pros = pros;
    }


}
