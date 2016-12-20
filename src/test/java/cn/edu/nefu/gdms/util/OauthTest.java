package cn.edu.nefu.gdms.util;

import cn.edu.nefu.gdms.oauth.CustomUserDetailsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by dingyunxiang on 16/12/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class OauthTest {
    @Autowired
    private CustomUserDetailsService service;

    @Test
    public void test(){
        UserDetails userDetails = service.loadUserByUsername("tea1");
    }

}
