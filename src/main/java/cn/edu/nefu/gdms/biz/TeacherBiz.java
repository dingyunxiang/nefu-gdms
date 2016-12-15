package cn.edu.nefu.gdms.biz;

import cn.edu.nefu.gdms.common.ErrorCodeEnum;
import cn.edu.nefu.gdms.common.TopicStatusEnum;
import cn.edu.nefu.gdms.common.UserTypeEnum;
import cn.edu.nefu.gdms.dao.UserDao;
import cn.edu.nefu.gdms.dto.TeacherDTO;
import cn.edu.nefu.gdms.exception.ServiceException;
import cn.edu.nefu.gdms.model.TopicPO;
import cn.edu.nefu.gdms.model.UserPO;
import cn.edu.nefu.gdms.util.FileUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dingyunxiang on 16/12/12.
 */
@Service
public class TeacherBiz {
    @Autowired
    private UserDao userDao;
    @Autowired
    private TopicBiz topicBiz;

    public long uploadTopic(TopicPO topicPO, MultipartFile file) {
        String filePath = FileUtils.getTeacherFilePath(topicPO.getYears(), topicPO.getTutorId());
        String fileName = file.getName();
        FileUtils.saveFile(filePath, fileName, file);
        topicPO.setFilePath(filePath + fileName);
        topicPO.setStatus(0);
        topicBiz.insert(topicPO);
        return 0;
    }


    public TeacherDTO get(long teaId) {
        return getTeacherDTO(userDao.get(teaId));
    }

    public TeacherDTO getTeacherByStuId(long stuId) {
        UserPO userPO = userDao.get(stuId);
        if (userPO.getTutorId() <= 0) {
            throw new ServiceException(ErrorCodeEnum.ROLE_NOT_STU);
        }
        return getTeacherDTO(userDao.get(userPO.getTutorId()));
    }

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

    private TeacherDTO getTeacherDTO(UserPO userPO) {
        TeacherDTO teacherDTO = new TeacherDTO();

        BeanUtils.copyProperties(userPO, teacherDTO);
        return teacherDTO;
    }

    private List<UserPO> getUserPOList(List<TeacherDTO> teacherDTOList) {
        List<UserPO> list = new ArrayList<UserPO>(teacherDTOList.size());

        for (TeacherDTO teacherDTO : teacherDTOList) {
            list.add(getUserPO(teacherDTO));
        }
        return list;
    }

}
