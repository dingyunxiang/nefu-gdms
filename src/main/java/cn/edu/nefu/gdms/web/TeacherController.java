package cn.edu.nefu.gdms.web;

import cn.edu.nefu.gdms.dto.Result;
import cn.edu.nefu.gdms.service.ServiceProxy;
import cn.edu.nefu.gdms.util.JsonUtils;
import cn.edu.nefu.gdms.util.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by dingyunxiang on 16/12/15.
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private ServiceProxy serviceProxy;

    @RequestMapping(value = "/getOwnStudents", method = RequestMethod.GET)
    @ResponseBody
    public String getOwnStudents(HttpServletRequest request) throws IOException {
        long id = Long.valueOf(RequestUtils.getUserNameFromHttpRequest(request));

        Result result = serviceProxy.getStudentsByTeaId(id);
        return JsonUtils.toString(request);
    }

    @RequestMapping(value = "/selfInfo", method = RequestMethod.GET)
    @ResponseBody
    public String getSelfInfo(HttpServletRequest request) throws IOException {
        long id = Long.valueOf(RequestUtils.getUserNameFromHttpRequest(request));

        Result result = serviceProxy.getTeacherDTOByTeaId(id);
        return JsonUtils.toString(request);
    }


    @RequestMapping(value = "/getAllStudents", method = RequestMethod.GET)
    @ResponseBody
    public String getAllStudent(@RequestParam("offset") int offset,
                                @RequestParam("limit") int limit,
                                @RequestParam("username") String username,
                                @RequestParam("name") String name) throws IOException {
        Result result = serviceProxy.getStudents(offset, limit, username, name);
        return JsonUtils.toString(result);
    }


}
