package cn.edu.nefu.gdms.web;

import cn.edu.nefu.gdms.biz.UserBiz;
import cn.edu.nefu.gdms.dto.Result;
import cn.edu.nefu.gdms.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by dingyunxiang on 16/12/5.
 */
@Controller
@RequestMapping("/userInfo")
public class UserController {

    @Autowired
    private UserBiz userBiz;

//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    @ResponseBody
//    public Result login(@RequestParam("username") String username,
//                        @RequestParam("password") String password) {
//        Result result = new Result();
//        if (userBiz.login(username, password)) {
//            return ResultUtils.getSuccessResult();
//        }
//        result.setSuccess(false);
//        result.setError("passwd is error!");
//        return result;
//    }


}
