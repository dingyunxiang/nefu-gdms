package cn.edu.nefu.gdms.web;

import cn.edu.nefu.gdms.biz.UserBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by dingyunxiang on 16/12/5.
 */
@Controller
@RequestMapping("userInfo")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserBiz userBiz;

    @RequestMapping(value = "login" ,method = RequestMethod.GET)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password){
        return userBiz.login(username,password)+"";
    }
}
