package cn.edu.nefu.gdms.oauth;

import cn.edu.nefu.gdms.biz.OauthBiz;
import cn.edu.nefu.gdms.biz.UserBiz;
import cn.edu.nefu.gdms.model.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by dingyunxiang on 16/12/20.
 */
@Service("CustomUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserBiz userBiz;
    @Autowired
    private OauthBiz oauthBiz;


    /**
     * 根据用户名查找用户信息，如果查找不到，抛出UsernameNotFoundException
     *
     * @param s 用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserPO userPO = userBiz.get(s);
        if (userPO == null) {
            throw new UsernameNotFoundException(String.format("username:%s not found!", s));
        }
        return new CustomUserDetails(userPO, oauthBiz);
    }
}
