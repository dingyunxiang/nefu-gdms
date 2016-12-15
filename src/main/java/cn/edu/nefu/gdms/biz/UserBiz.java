package cn.edu.nefu.gdms.biz;

import cn.edu.nefu.gdms.dao.UserDao;
import cn.edu.nefu.gdms.model.UserPO;
import cn.edu.nefu.gdms.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dingyunxiang on 16/12/5.
 */
@Service
public class UserBiz {

    @Autowired
    private UserDao userDao;

    public boolean login(String username, String password) {
        UserPO userPO = userDao.find(username);
        if (userPO != null) {
            return MD5Utils.MD5(password).equals(userPO.getPassword());
        }

        return false;
    }

    public boolean updatePasswd(long id, String password) {
        UserPO userPO = userDao.get(id);

        userPO.setPassword(MD5Utils.MD5(password));
        userPO.setUpdateTime(System.currentTimeMillis());
        return userDao.update(userPO) > 0;
    }

    public List<UserPO> getUserByIds(List<Long> list) {
        return userDao.getByIds(list);
    }

    public boolean updateUserPOs(List<UserPO> list) {
        for (UserPO userPO : list) {
            userPO.setUpdateTime(System.currentTimeMillis());
        }
        return userDao.updateList(list) == list.size();
    }
}
