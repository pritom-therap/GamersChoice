package net.therap.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * user: pritom
 * Date: 5/30/12
 * Time: 2:33 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "PT_USER")
public class User {
    int userId;
    String userName;
    Date birthday;
    String email;
    String password;
    Date joinDate;
    long version;
    String status;
    int playedGameCount;
    List<GameReview> playedGames;
    List<Game> trackedGames;
    List<TrackedUser> trackedUsers;
    List<TrackedUser> requestingUsers;
    UserRatingHistory userRatingHistory;
    UserGenreHistory userGenreHistory;

    boolean approved;
    boolean requested;
    boolean rejected;


    @OneToMany(mappedBy = "gameReviewId.user")
    public List<GameReview> getPlayedGames() {
        return playedGames;
    }

    public void setPlayedGames(List<GameReview> playedGames) {
        this.playedGames = playedGames;
    }


    @ManyToMany
    @JoinTable(name = "PT_TRACKED_GAME", joinColumns = {
            @JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "GAME_ID")}
    )
    public List<Game> getTrackedGames() {
        return trackedGames;
    }

    public void setTrackedGames(List<Game> trackedGames) {
        this.trackedGames = trackedGames;
    }

    @OneToMany(mappedBy = "trackedUserId.user")
    public List<TrackedUser> getTrackedUsers() {
        return trackedUsers;
    }

    public void setTrackedUsers(List<TrackedUser> trackedUsers) {
        this.trackedUsers = trackedUsers;
    }

    @OneToMany(mappedBy = "trackedUserId.trackedUser")
    public List<TrackedUser> getRequestingUsers() {
        return requestingUsers;
    }

    public void setRequestingUsers(List<TrackedUser> requestingUsers) {
        this.requestingUsers = requestingUsers;
    }


    @Id
    @SequenceGenerator(name = "PT_USER_SEQ", sequenceName = "PT_USER_SEQ")
        @GeneratedValue(strategy = GenerationType.AUTO, generator = "PT_USER_SEQ")

    @Column(name = "USER_ID")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "USER_NAME")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTHDAY")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "JOIN_DATE")
    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
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

    @OneToOne(mappedBy = "user")
    public UserRatingHistory getUserRatingHistory() {
        return userRatingHistory;
    }

    public void setUserRatingHistory(UserRatingHistory userRatingHistory) {
        this.userRatingHistory = userRatingHistory;
    }


    @OneToOne(mappedBy = "user")
    public UserGenreHistory getUserGenreHistory() {
        return userGenreHistory;
    }

    public void setUserGenreHistory(UserGenreHistory userGenreHistory) {
        this.userGenreHistory = userGenreHistory;
    }

    @Column(name = "PLAYED_GAME_COUNT")
    public int getPlayedGameCount() {
        return playedGameCount;
    }

    public void setPlayedGameCount(int playedGameCount) {
        this.playedGameCount = playedGameCount;
    }


    @Transient
    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    @Transient
    public boolean isRequested() {
        return requested;
    }

    public void setRequested(boolean requested) {
        this.requested = requested;
    }

    @Transient
    public boolean isRejected() {
        return rejected;
    }

    public void setRejected(boolean rejected) {
        this.rejected = rejected;
    }

}
