package cn.edu.nefu.gdms.biz;

import cn.edu.nefu.gdms.common.UserTypeEnum;
import cn.edu.nefu.gdms.dao.UserDao;
import cn.edu.nefu.gdms.dto.StudentDTO;
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

    public boolean chooseTutor(long studentId, long tutorId) {
        UserPO userPO = userDao.get(studentId);

        userPO.setTutorId(tutorId);
        return userDao.update(userPO) > 0;
    }


    public List<StudentDTO> getStudentDTOList(int offset, int size, String username, String name) {
        return getStudentDTOList(userDao.findByType(UserTypeEnum.STUDENT.getValue(), offset, size, username, name));
    }


    private List<UserPO> getUserPOList(List<StudentDTO> studentDTOList) {
        List<UserPO> list = new ArrayList<UserPO>(studentDTOList.size());

        for (StudentDTO studentDTO : studentDTOList) {
            list.add(getUserPO(studentDTO));
        }
        return list;
    }

    public List<StudentDTO> getStudentsByTutorId(long tutorId) {
        return getStudentDTOList(userDao.findByTutorId(tutorId));
    }


    private UserPO getUserPO(StudentDTO studentDTO) {
        UserPO userPO = new UserPO();
//        userPO.setUsername(studentDTO.getUsername());
//        userPO.setEmail(studentDTO.getEmail());
//        userPO.setPhone(studentDTO.getPhone());
//        userPO.setClasses(studentDTO.getClasses());
//        userPO.setName(studentDTO.getName());

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

}
