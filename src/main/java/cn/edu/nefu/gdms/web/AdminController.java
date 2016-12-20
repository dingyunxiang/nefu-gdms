package cn.edu.nefu.gdms.web;

import cn.edu.nefu.gdms.dto.Result;
import cn.edu.nefu.gdms.dto.StudentDTO;
import cn.edu.nefu.gdms.dto.TeacherDTO;
import cn.edu.nefu.gdms.model.TimePeriod;
import cn.edu.nefu.gdms.service.ServiceProxy;
import cn.edu.nefu.gdms.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Created by dingyunxiang on 16/12/19.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ServiceProxy serviceProxy;

    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    @ResponseBody
    public String addStudent(@RequestBody StudentDTO studentDTO) throws IOException {
        Result result = serviceProxy.addStudent(studentDTO);
        return JsonUtils.toString(result);
    }

    @RequestMapping(value = "/addTeacher", method = RequestMethod.POST)
    @ResponseBody
    public String addStudent(@RequestBody TeacherDTO teacherDTO) throws IOException {
        Result result = serviceProxy.addTeacher(teacherDTO);
        return JsonUtils.toString(result);
    }

    @RequestMapping(value = "/addTimePeriod", method = RequestMethod.POST)
    @ResponseBody
    public String addStudent(@RequestBody TimePeriod timePeriod) throws IOException {
        Result result = serviceProxy.addTimePeriod(timePeriod);
        return JsonUtils.toString(result);
    }

    @RequestMapping(value = "/getAllTeachers", method = RequestMethod.GET)
    @ResponseBody
    public String getAllTeacher(@RequestParam("offset") int offset,
                                @RequestParam("limit") int limit,
                                @RequestParam("username") String username,
                                @RequestParam("name") String name) throws IOException {
        Result result = serviceProxy.getTeachers(offset, limit, username, name);
        return JsonUtils.toString(result);
    }

}
