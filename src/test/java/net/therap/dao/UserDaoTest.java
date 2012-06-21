package net.therap.dao;

import net.therap.domain.User;
import org.junit.Assert;
import org.testng.annotations.Test;
import org.unitils.UnitilsTestNG;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.orm.hibernate.HibernateUnitils;
import org.unitils.reflectionassert.ReflectionAssert;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: pritom
 * Date: 6/19/12
 * Time: 12:48 PM
 * To change this template use File | Settings | File Templates.
 */
@DataSet
@SpringApplicationContext(value = {"classpath:test-applicationContext.xml"})
public class UserDaoTest extends UnitilsTestNG {

    @SpringBean("userDao")
    UserDao userDao;

    @Test
    public void testMappingToDatabase() {
        HibernateUnitils.assertMappingWithDatabaseConsistent();
    }

    @Test
    public void testGetUsers() {

        List<User> users = userDao.getUsers();

        Assert.assertEquals(users.size(), 2);
        ReflectionAssert.assertPropertyLenientEquals("userName", Arrays.asList("ayon", "tahmid"), users);
    }


}
