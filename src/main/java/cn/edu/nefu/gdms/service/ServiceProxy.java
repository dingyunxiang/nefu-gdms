package cn.edu.nefu.gdms.service;

import cn.edu.nefu.gdms.aop.ErrorHandler;
import cn.edu.nefu.gdms.aop.Log;
import cn.edu.nefu.gdms.biz.StudentBiz;
import cn.edu.nefu.gdms.biz.TeacherBiz;
import cn.edu.nefu.gdms.dto.QueryResult;
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

    public Result getStudentDTOByStuId(long stuId) {
        Result result = ResultUtils.getSuccessResult();
        result.setData(studentBiz.get(stuId));
        return result;
    }

    public Result getTeacherDTOByTeaId(long teaId) {
        Result result = ResultUtils.getSuccessResult();
        result.setData(teacherBiz.get(teaId));
        return result;
    }

    public Result getStudentsByTeaId(long teaId) {
        Result result = ResultUtils.getSuccessResult();
        result.setData(studentBiz.getStudentsByTutorId(teaId));
        return result;
    }

    public Result getStudents(int offset, int limit, String name, String username) {
        Result result = ResultUtils.getSuccessResult();
        QueryResult queryResult = transToQueryResult(offset, limit, studentBiz.getStudentDTOList(offset, limit, username, name));
        result.setData(queryResult);
        return result;
    }



    private QueryResult transToQueryResult(int offset, int limit, Object object) {
        QueryResult queryResult = new QueryResult();
        queryResult.setData(object);
        queryResult.setOffset(offset);
        queryResult.setLimit(limit);
        return queryResult;
    }

}
