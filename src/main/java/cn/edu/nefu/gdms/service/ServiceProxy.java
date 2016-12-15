package cn.edu.nefu.gdms.service;

import cn.edu.nefu.gdms.aop.ErrorHandler;
import cn.edu.nefu.gdms.aop.Log;
import cn.edu.nefu.gdms.biz.StudentBiz;
import cn.edu.nefu.gdms.biz.TeacherBiz;
import cn.edu.nefu.gdms.dto.Result;
import cn.edu.nefu.gdms.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dingyunxiang on 16/12/15.
 */
@Log
@ErrorHandler
@Service
public class ServiceProxy {

    @Autowired
    private StudentBiz studentBiz;

    @Autowired
    private TeacherBiz teacherBiz;

    public Result getTutorByStuId(long stuId) {
        Result result = ResultUtils.getSuccessResult();
        result.setData(teacherBiz.getTeacherByStuId(stuId));
        return result;
    }

}
