package cn.edu.nefu.gdms.dao;

import cn.edu.nefu.gdms.dto.StudentDTO;
import cn.edu.nefu.gdms.model.UserPO;
import cn.edu.nefu.gdms.util.EqualMap;

import java.util.ArrayList;
import java.util.List;

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

    public static List<StudentDTO> getStudentDTOList(){
        List<StudentDTO> list = new ArrayList<StudentDTO>();
        for(int i = 0;i<20;i++){
            StudentDTO studentDTO = new StudentDTO();

            studentDTO.setEmail("12109@qq.com");
            studentDTO.setClasses("三年二班");
            studentDTO.setId(i);
            studentDTO.setUsername("2013"+i);
            studentDTO.setPhone("13211111111");
            studentDTO.setName("veblen");

            list.add(studentDTO);
        }
        return list;
    }

    public static EqualMap<String,String> getEqualMap(){
        EqualMap<String, String> map = new EqualMap<String, String>();

        map.putVal("username", "学号");
        map.putVal("name", "姓名");
        map.putVal("classes", "班级");
        map.putVal("email", "邮箱");
        map.putVal("phone", "电话");
        return map;
    }

}
