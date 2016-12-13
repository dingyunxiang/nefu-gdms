package cn.edu.nefu.gdms.biz;

import cn.edu.nefu.gdms.common.ErrorCodeEnum;
import cn.edu.nefu.gdms.common.UserTypeEnum;
import cn.edu.nefu.gdms.dao.UserDao;
import cn.edu.nefu.gdms.dto.StudentDTO;
import cn.edu.nefu.gdms.exception.ServiceException;
import cn.edu.nefu.gdms.model.TopicPO;
import cn.edu.nefu.gdms.model.UserPO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dingyunxiang on 16/12/12.
 */
public class StudentBiz {
    @Autowired
    private UserDao userDao;
    @Autowired
    private TopicBiz topicBiz;

    //选题
    public boolean chooseTopic(long stuId, long topicId) {
        UserPO userPO = userDao.get(stuId);
        TopicPO topicPO = topicBiz.get(topicId);
        if (userPO == null || userPO.getTutorId() < 0) {
            throw new ServiceException(ErrorCodeEnum.ROLE_NOT_STU);
        }
        if (topicPO.getTutorId() != userPO.getTutorId()) {
            throw new ServiceException(ErrorCodeEnum.CALL_BSERVICE_ERROR);
        }

        topicPO.setStuId(stuId);
        return topicBiz.update(topicPO);
    }

    //选择导师
    public boolean chooseTutor(long studentId, long tutorId) {
        UserPO userPO = userDao.get(studentId);

        userPO.setTutorId(tutorId);
        return userDao.update(userPO) > 0;
    }

    public List<TopicPO> listTopics(long studentId) {
        UserPO userPO = userDao.get(studentId);
        return topicBiz.getTopicsByTeaId(userPO.getTutorId());
    }

    public long insertStudent(StudentDTO studentDTO) {
        UserPO userPO = getUserPO(studentDTO);

        userPO.setCreateTime(System.currentTimeMillis());
        userPO.setUpdateTime(userPO.getCreateTime());
        return userDao.insert(userPO);
    }

    public void insertStudentList(List<StudentDTO> studentDTOList) {
        List<UserPO> list = getUserPOList(studentDTOList);

        for (UserPO userPO : list) {
            userPO.setCreateTime(System.currentTimeMillis());
            userPO.setUpdateTime(userPO.getCreateTime());
        }
        userDao.insertList(getUserPOList(studentDTOList));
    }


    public List<StudentDTO> getStudentDTOList(int offset, int size, String username, String name) {
        return getStudentDTOList(userDao.findByType(UserTypeEnum.STUDENT.getValue(), offset, size, username, name));
    }


    public List<StudentDTO> getStudentsByTutorId(long tutorId) {
        return getStudentDTOList(userDao.findByTutorId(tutorId));
    }


    private UserPO getUserPO(StudentDTO studentDTO) {
        UserPO userPO = new UserPO();

        BeanUtils.copyProperties(studentDTO, userPO);
        //设置学生type
        userPO.setTypeId(UserTypeEnum.STUDENT.getValue());
        return userPO;
    }


    private StudentDTO getStudentDTO(UserPO userPO) {
        StudentDTO studentDTO = new StudentDTO();

        BeanUtils.copyProperties(userPO, studentDTO);
        return studentDTO;
    }


    private List<StudentDTO> getStudentDTOList(List<UserPO> userPOList) {
        List<StudentDTO> list = new ArrayList<StudentDTO>(userPOList.size());

        for (UserPO userPO : userPOList) {
            list.add(getStudentDTO(userPO));
        }
        return list;
    }

    private List<UserPO> getUserPOList(List<StudentDTO> studentDTOList) {
        List<UserPO> list = new ArrayList<UserPO>(studentDTOList.size());

        for (StudentDTO studentDTO : studentDTOList) {
            list.add(getUserPO(studentDTO));
        }
        return list;
    }

}
