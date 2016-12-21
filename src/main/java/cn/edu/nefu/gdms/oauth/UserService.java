package cn.edu.nefu.gdms.oauth;

import cn.edu.nefu.gdms.biz.UserBiz;
import cn.edu.nefu.gdms.model.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by dingyunxiang on 16/12/20.
 */
public class UserService {
    @Autowired
    private UserBiz userBiz;


    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        final UserPO userPO = userBiz.get(s);
        return null;
    }
}
