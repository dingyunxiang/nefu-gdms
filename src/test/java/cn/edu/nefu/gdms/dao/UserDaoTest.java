package cn.edu.nefu.gdms.dao;

import cn.edu.nefu.gdms.model.UserPO;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dingyunxiang on 16/12/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testInsert(){
        UserPO userPO = TestDataHelper.getUser();
        long rs = userDao.insert(userPO);
        System.out.println(userPO.getId());
    }

    @Test
    public void testGet(){
        UserPO get = userDao.get(20134270);
        UserPO find = userDao.find("20134269");
        get.setUsername("updateName");
        System.out.println(get);
        System.out.println(find);
        System.out.println(userDao.update(get));
    }

    @Test
    public void testDelete(){
        System.out.println(userDao.delete(20134269));
    }

    @Test
    public void testInsertLisy(){
        List<UserPO> list = new ArrayList<UserPO>();
        list.add(TestDataHelper.getUser());
        list.add(TestDataHelper.getUser());

        userDao.insertList(list);
    }
}