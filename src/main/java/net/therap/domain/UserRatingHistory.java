package net.therap.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * user: pritom
 * Date: 5/30/12
 * Time: 2:43 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "PT_USER_RATING_HISTORY")
public class UserRatingHistory {

    int userId;
    User user;
    float averageRatingPresentation;
    float averageRatingGraphics;
    float averageRatingSound;
    float averageRatingGamePlay;
    float averageRatingLongevity;
    float averageRatingDifficulty;
    float averageRatingGameLength;
    long version;

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

    @Column(name = "AVERAGE_RATING_PRESENTATION")
    public float getAverageRatingPresentation() {
        return averageRatingPresentation;
    }

    public void setAverageRatingPresentation(float averageRatingPresentation) {
        this.averageRatingPresentation = averageRatingPresentation;
    }

    @Column(name = "AVERAGE_RATING_GRAPHICS")
    public float getAverageRatingGraphics() {
        return averageRatingGraphics;
    }

    public void setAverageRatingGraphics(float averageRatingGraphics) {
        this.averageRatingGraphics = averageRatingGraphics;
    }


    @Column(name = "AVERAGE_RATING_SOUND")
    public float getAverageRatingSound() {
        return averageRatingSound;
    }

    public void setAverageRatingSound(float averageRatingSound) {
        this.averageRatingSound = averageRatingSound;
    }


    @Column(name = "AVERAGE_RATING_GAMEPLAY")
    public float getAverageRatingGamePlay() {
        return averageRatingGamePlay;
    }

    public void setAverageRatingGamePlay(float averageRatingGamePlay) {
        this.averageRatingGamePlay = averageRatingGamePlay;
    }


    @Column(name = "AVERAGE_RATING_LONGEVITY")
    public float getAverageRatingLongevity() {
        return averageRatingLongevity;
    }

    public void setAverageRatingLongevity(float averageRatingLongevity) {
        this.averageRatingLongevity = averageRatingLongevity;
    }

    @Column(name = "AVERAGE_RATING_DIFFICULTY")
    public float getAverageRatingDifficulty() {
        return averageRatingDifficulty;
    }

    public void setAverageRatingDifficulty(float averageRatingDifficulty) {
        this.averageRatingDifficulty = averageRatingDifficulty;
    }

    @Column(name = "AVERAGE_RATING_GAME_LENGTH")
    public float getAverageRatingGameLength() {
        return averageRatingGameLength;
    }

    public void setAverageRatingGameLength(float averageRatingGameLength) {
        this.averageRatingGameLength = averageRatingGameLength;
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

