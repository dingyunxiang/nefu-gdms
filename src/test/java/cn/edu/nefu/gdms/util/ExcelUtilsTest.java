package cn.edu.nefu.gdms.util;

import cn.edu.nefu.gdms.dao.TestDataHelper;
import cn.edu.nefu.gdms.dto.StudentDTO;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dingyunxiang on 16/12/13.
 */
public class ExcelUtilsTest {
    @Test
    public void obatainList() throws Exception {
        File file = new File("file/test.xls");
        ExcelUtils<StudentDTO> excelUtils = new ExcelUtils<StudentDTO>();
        EqualMap<String, String> map = TestDataHelper.getEqualMap();
        List<StudentDTO> list = excelUtils.obatainList(file, StudentDTO.class, map);
        for(StudentDTO studentDTO:list){
            System.out.println(studentDTO);
        }
    }



    @Test
    public void obtainFile() throws Exception {
        List<StudentDTO> list = TestDataHelper.getStudentDTOList();

        ExcelUtils<StudentDTO> excelUtils = new ExcelUtils<StudentDTO>();
        EqualMap<String, String> map = TestDataHelper.getEqualMap();

        File file = excelUtils.obtainFile(list, StudentDTO.class, "file/test.xls", map);
    }

    @Test
    public void testSet() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "姓名");
        map.put("email", "邮箱");
        map.put("phone", "电话");
        map.put("username", "学号");
        map.put("classes", "班级");

        Object[] arr = map.keySet().toArray();
        System.out.println(arr[0]);
    }

}