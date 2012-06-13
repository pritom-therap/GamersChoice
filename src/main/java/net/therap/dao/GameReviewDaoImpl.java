package net.therap.dao;

import net.therap.domain.GameReview;
import net.therap.domain.User;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: pritom
 * Date: 5/31/12
 * Time: 11:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class GameReviewDaoImpl extends HibernateDaoSupport implements GameReviewDao{

    protected final Logger logger = Logger.getLogger(this.getClass());

    public void saveGameReview(GameReview gameReview) {



        Session session = getSession();
        session.saveOrUpdate(gameReview);
        session.flush();


    }


}
