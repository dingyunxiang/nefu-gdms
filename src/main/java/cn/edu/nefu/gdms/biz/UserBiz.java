package cn.edu.nefu.gdms.biz;

import cn.edu.nefu.gdms.dao.UserDao;
import cn.edu.nefu.gdms.model.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            return password.equals(userPO.getPassword());
        }
        return false;
    }
}
