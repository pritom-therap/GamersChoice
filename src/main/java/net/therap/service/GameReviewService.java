package net.therap.service;

import net.therap.command.AddReview;
import net.therap.domain.User;

/**
 * Created by IntelliJ IDEA.
 * User: pritom
 * Date: 6/4/12
 * Time: 12:38 PM
 * To change this template use File | Settings | File Templates.
 */
public interface GameReviewService {

    public void addReview(AddReview addReview, User user);
}
