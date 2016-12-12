package cn.edu.nefu.gdms.dao;

import cn.edu.nefu.gdms.dto.StudentDTO;
import cn.edu.nefu.gdms.model.UserPO;

/**
 * Created by dingyunxiang on 16/12/5.
 */
public class TestDataHelper {
    public static UserPO getUser() {
        UserPO userPO = new UserPO();
        userPO.setEmail("1210955086@qq.com");
        userPO.setUsername("20134268");
        userPO.setPassword("qwert");
        userPO.setPhone("18646534278");
        userPO.setTutorId(0L);
        userPO.setCreateTime(System.currentTimeMillis());
        userPO.setUpdateTime(userPO.getCreateTime());

        return userPO;
    }


    public static StudentDTO getStudentDTO() {
        StudentDTO studentDTO = new StudentDTO();

        studentDTO.setName("name");
        studentDTO.setClasses("classes");
        studentDTO.setEmail("email");
        studentDTO.setPhone("phone");

        return studentDTO;
    }

}
