package net.therap.service.game;

import net.therap.command.ReviewCmd;
import net.therap.domain.GameReview;
import net.therap.domain.User;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * user: pritom
 * Date: 6/4/12
 * Time: 12:38 PM
 * To change this template use File | Settings | File Templates.
 */
public interface GameReviewService {

    public void addReview(ReviewCmd reviewCmd, User user);

    public List<GameReview> getGamesReviewedByUser(User user);
}
