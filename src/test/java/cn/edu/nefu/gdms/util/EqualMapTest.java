package cn.edu.nefu.gdms.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by dingyunxiang on 16/12/13.
 */
public class EqualMapTest {
    @Test
    public void keySet() throws Exception {
        EqualMap<String,String> map = new EqualMap<String, String>();
        map.putVal("name","姓名");
        map.putVal("email","邮箱");
        map.putVal("phone","电话");
        map.putVal("username","学号");
        map.putVal("classes","班级");

////        String[] str = map.keySet();
//        System.out.println(str[1]);
    }

}