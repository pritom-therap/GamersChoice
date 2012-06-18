package net.therap.domain;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
<<<<<<< HEAD
 * user: pritom
=======
 * User: pritom
>>>>>>> 958d29b8c056bfe7629bdb3f882003e9797e4933
 * Date: 5/31/12
 * Time: 12:23 PM
 * To change this template use File | Settings | File Templates.
 */

@Embeddable
public class GameReviewId implements Serializable{

    private Game game;
    private User user;


    @ManyToOne
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }


    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
