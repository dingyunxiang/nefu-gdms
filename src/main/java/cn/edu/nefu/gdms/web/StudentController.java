package cn.edu.nefu.gdms.web;

import cn.edu.nefu.gdms.dto.Result;
import cn.edu.nefu.gdms.service.ServiceProxy;
import cn.edu.nefu.gdms.util.JsonUtils;
import cn.edu.nefu.gdms.util.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by dingyunxiang on 16/12/14.
 */
@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private ServiceProxy serviceProxy;


    @RequestMapping(value = "/getTutor", method = RequestMethod.GET)
    @ResponseBody
    public String getTutor(HttpServletRequest request) throws IOException {
        long id = Long.valueOf(RequestUtils.getUserNameFromHttpRequest(request));

        Result result = serviceProxy.getTutorByStuId(id);
        return JsonUtils.toString(request);
    }

    @RequestMapping(value = "/selfInfo", method = RequestMethod.GET)
    @ResponseBody
    public String getSelfInfo(HttpServletRequest request) throws IOException {
        long id = Long.valueOf(RequestUtils.getUserNameFromHttpRequest(request));

        Result result = serviceProxy.getStudentDTOByStuId(id);
        return JsonUtils.toString(request);
    }

    @RequestMapping(value = "/chooseTopic", method = RequestMethod.GET)
    @ResponseBody
    public String chooseTopic(@RequestParam("topicId") long topicId,
                              HttpServletRequest request) throws IOException {
        long id = Long.valueOf(RequestUtils.getUserNameFromHttpRequest(request));

        Result result = serviceProxy.chooseTopic(id, topicId);
        return JsonUtils.toString(request);
    }

    @RequestMapping(value = "/allTopic", method = RequestMethod.GET)
    @ResponseBody
    public String allTopic(HttpServletRequest request) throws IOException {
        long id = Long.valueOf(RequestUtils.getUserNameFromHttpRequest(request));

        Result result = serviceProxy.listAllTopicForStu(id);
        return JsonUtils.toString(request);
    }
}
