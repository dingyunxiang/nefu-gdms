package cn.edu.nefu.gdms.dao;

import cn.edu.nefu.gdms.dto.StudentDTO;
import org.junit.Test;

import javax.xml.bind.SchemaOutputResolver;
import java.lang.reflect.Field;

/**
 * Created by dingyunxiang on 16/12/13.
 */
public class ReflectTest {

    @Test
    public void test(){
        StudentDTO studentDTO = TestDataHelper.getStudentDTO();

        Field[] fileds = studentDTO.getClass().getDeclaredFields();



        System.out.println(fileds.length);

        for(Field field:fileds){
            try {
                field.setAccessible(true);
                System.out.println(field.getGenericType());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    @Test
    public void testInstanceOf() throws Exception{
    }

}
