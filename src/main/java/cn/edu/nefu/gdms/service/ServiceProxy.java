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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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

    public Result getStudents(int offset, int limit, String username, String name) {
        Result result = ResultUtils.getSuccessResult();
        QueryResult queryResult = transToQueryResult(offset, limit, studentBiz.getStudentDTOList(offset, limit, username, name), studentBiz.getStudentDTOListSize(username, name));
        result.setData(queryResult);
        return result;
    }

    public Result setTutor(long tutorId, long stuId) {
        Result result = ResultUtils.getSuccessResult();
        studentBiz.chooseTutor(stuId, tutorId);
        return result;
    }

    public Result cancelTutor(long tutorId, long stuId) {
        Result result = ResultUtils.getSuccessResult();
        studentBiz.cancelTutor(stuId, tutorId);
        return result;
    }

    public Result chooseTopic(long stuId, long topicId) {
        Result result = ResultUtils.getSuccessResult();
        studentBiz.chooseTopic(stuId, topicId);
        return result;
    }

    private QueryResult transToQueryResult(int offset, int limit, Object object, int total) {
        QueryResult queryResult = new QueryResult();
        queryResult.setData(object);
        queryResult.setOffset(offset);
        queryResult.setLimit(limit);
        queryResult.setTotal(total);
        return queryResult;
    }

    public Result uploadChooseReport(long tutorId, long stuId, MultipartFile file) throws IOException {
        Result result = ResultUtils.getSuccessResult();

        studentBiz.uploadChooseReport(stuId, tutorId, file);
        return result;
    }

    public Result listAllTopicForStu(long stuId) {
        Result result = ResultUtils.getSuccessResult();
        result.setData(studentBiz.listTopics(stuId));
        return result;
    }

    public File downChoostReport(long stuId) {
        return studentBiz.downChooseReport(stuId);
    }
}
