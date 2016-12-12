package cn.edu.nefu.gdms.biz;

import cn.edu.nefu.gdms.common.UserTypeEnum;
import cn.edu.nefu.gdms.dao.UserDao;
import cn.edu.nefu.gdms.dto.StudentDTO;
import cn.edu.nefu.gdms.dto.TeacherDTO;
import cn.edu.nefu.gdms.model.UserPO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dingyunxiang on 16/12/12.
 */
public class TeacherBiz {
    @Autowired
    private UserDao userDao;

    public long insertTeacher(TeacherDTO teacherDTO) {
        UserPO userPO = getUserPO(teacherDTO);

        userPO.setCreateTime(System.currentTimeMillis());
        userPO.setUpdateTime(userPO.getCreateTime());
        return userDao.insert(userPO);
    }

    public void insertTeacherList(List<TeacherDTO> teacherDTOList) {
        List<UserPO> list = getUserPOList(teacherDTOList);

        for (UserPO userPO : list) {
            userPO.setCreateTime(System.currentTimeMillis());
            userPO.setUpdateTime(userPO.getCreateTime());
        }
        userDao.insertList(list);
    }



    private UserPO getUserPO(TeacherDTO teacherDTO) {
        UserPO userPO = new UserPO();

        BeanUtils.copyProperties(teacherDTO, userPO);
        userPO.setTypeId(UserTypeEnum.TEACHER.getValue());
        return userPO;
    }

    private List<UserPO> getUserPOList(List<TeacherDTO> teacherDTOList) {
        List<UserPO> list = new ArrayList<UserPO>(teacherDTOList.size());

        for (TeacherDTO teacherDTO : teacherDTOList) {
            list.add(getUserPO(teacherDTO));
        }
        return list;
    }

}
