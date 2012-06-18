package net.therap.domain;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * user: pritom
 * Date: 5/31/12
 * Time: 12:20 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "PT_TRACKED_USER")
@AssociationOverrides({
        @AssociationOverride(name = "trackedUserId.user", joinColumns = @JoinColumn(name = "USER_ID")),
        @AssociationOverride(name = "trackedUserId.trackedUser", joinColumns = @JoinColumn(name = "TRACKED_USER_ID"))
})
public class  TrackedUser {

    private TrackedUserId trackedUserId = new TrackedUserId();
    String isApproved;
    long version;

    @EmbeddedId
    public TrackedUserId getTrackedUserId() {
        return trackedUserId;
    }

    public void setTrackedUserId(TrackedUserId trackedUserId) {
        this.trackedUserId = trackedUserId;
    }

    @Transient
    public User getUser() {
        return getTrackedUserId().getUser();
    }

    public void setUser(User user) {
        getTrackedUserId().setUser(user);
    }

    @Transient
    public User getTrackedUser() {
        return getTrackedUserId().getTrackedUser();
    }

    public void setTrackedUser(User trackedUser) {
        getTrackedUserId().setTrackedUser(trackedUser);
    }

    @Column(name = "IS_APPROVED")
    public String getApproved() {
        return isApproved;
    }

    public void setApproved(String approved) {
        isApproved = approved;
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
