package cn.edu.nefu.gdms.dao;

import cn.edu.nefu.gdms.dto.StudentDTO;
import cn.edu.nefu.gdms.model.UserPO;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

/**
 * Created by dingyunxiang on 16/12/12.
 */
public class BeanUtilsTest {

    @Test
    public void test(){
        StudentDTO studentDTO = TestDataHelper.getStudentDTO();

        UserPO userPO = new UserPO();

        BeanUtils.copyProperties(studentDTO,userPO);

        System.out.println(studentDTO);
        System.out.println(userPO);

    }

}
