package cn.edu.nefu.gdms.web;

import cn.edu.nefu.gdms.dto.Result;
import cn.edu.nefu.gdms.service.ServiceProxy;
import cn.edu.nefu.gdms.util.FileUtils;
import cn.edu.nefu.gdms.util.JsonUtils;
import cn.edu.nefu.gdms.util.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
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

    @RequestMapping(value = "/setTutor", method = RequestMethod.GET)
    @ResponseBody
    public String setTutor(@RequestParam("studentId") int stuId,
                           HttpServletRequest request) throws IOException {
        long id = Long.valueOf(RequestUtils.getUserNameFromHttpRequest(request));

        Result result = serviceProxy.setTutor(id, stuId);
        return JsonUtils.toString(result);
    }

    @RequestMapping(value = "/cancelStu", method = RequestMethod.GET)
    @ResponseBody
    public String cancelStu(@RequestParam("studentId") int stuId,
                            HttpServletRequest request) throws IOException {
        long id = Long.valueOf(RequestUtils.getUserNameFromHttpRequest(request));

        Result result = serviceProxy.cancelTutor(id, stuId);
        return JsonUtils.toString(result);
    }

    @RequestMapping(value = "/uploadChooseReport", method = RequestMethod.GET)
    @ResponseBody
    public String cancelStu(@RequestParam("studentId") int stuId,
                            @RequestParam(value = "file") MultipartFile file,
                            HttpServletRequest request) throws IOException {
        long teaId = Long.valueOf(RequestUtils.getUserNameFromHttpRequest(request));

        Result result = serviceProxy.uploadChooseReport(teaId, stuId, file);
        return JsonUtils.toString(result);
    }

    @RequestMapping(value = "/downloadChooseReport", method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadChooseReport(@RequestParam("studentId") int stuId) throws IOException {
        File file = serviceProxy.downChoostReport(stuId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", file.getName());
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }


}
