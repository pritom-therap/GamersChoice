package net.therap.dao;

import net.therap.domain.TrackedUser;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Created by IntelliJ IDEA.
 * user: pritom
 * Date: 6/13/12
 * Time: 11:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class TrackUserDaoImpl extends HibernateDaoSupport implements TrackUserDao{

    public void saveRequest(TrackedUser trackedUser) {

        Session session = getSession();
        session.saveOrUpdate(trackedUser);
        session.flush();
    }
}
