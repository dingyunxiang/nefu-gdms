package cn.edu.nefu.gdms.oauth;

import cn.edu.nefu.gdms.biz.UserBiz;
import cn.edu.nefu.gdms.model.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.xml.ws.Action;
import java.util.Collection;

/**
 * Created by dingyunxiang on 16/12/20.
 */
public class UserService extends UserDetailsService {
    @Autowired
    private UserBiz userBiz;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        final UserPO userPO = userBiz.get(s);

    }
}
