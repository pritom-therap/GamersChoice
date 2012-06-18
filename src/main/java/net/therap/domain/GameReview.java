package net.therap.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * user: pritom
 * Date: 5/30/12
 * Time: 3:05 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "PT_GAME_REVIEW")
@AssociationOverrides({
        @AssociationOverride(name = "gameReviewId.game", joinColumns = @JoinColumn(name = "GAME_ID")),
        @AssociationOverride(name = "gameReviewId.user", joinColumns = @JoinColumn(name = "USER_ID"))
})
public class GameReview implements Comparable{

    private GameReviewId gameReviewId = new GameReviewId();
    String pros;
    String cons;
    Date reviewDate;
    float ratingPresentation;
    float ratingGraphics;
    float ratingSound;
    float ratingGamePlay;
    float ratingLongevity;
    float gameLength;
    int difficulty;
    long version;

    @EmbeddedId
    public GameReviewId getGameReviewId() {
        return gameReviewId;
    }

    public void setGameReviewId(GameReviewId gameReviewId) {
        this.gameReviewId = gameReviewId;
    }

    @Transient
    public User getUser(){
        return getGameReviewId().getUser();
    }

    public void setUser(User user) {
         getGameReviewId().setUser(user);
    }

    @Transient
    public Game getGame(){
        return getGameReviewId().getGame();
    }

    public void setGame(Game game) {
         getGameReviewId().setGame(game);
    }

    @Column(name = "PROS")
    public String getPros() {
        return pros;
    }

    public void setPros(String pros) {
        this.pros = pros;
    }

    @Column(name = "CONS")
    public String getCons() {
        return cons;
    }

    public void setCons(String cons) {
        this.cons = cons;
    }


    @Temporal(TemporalType.DATE)
    @Column(name = "REVIEW_DATE")
    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
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

    @Column(name = "RATING_LONGETIVITY")
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

    @Column(name = "DIFFICULTY")
    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    @Version
    @Column(name = "VERSION")
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }


    public int compareTo(Object o) {
       GameReview gameReview = (GameReview)o;
       return this.getReviewDate().compareTo(gameReview.getReviewDate());

    }
}
