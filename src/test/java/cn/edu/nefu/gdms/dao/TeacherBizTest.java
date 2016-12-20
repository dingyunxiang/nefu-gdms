package cn.edu.nefu.gdms.dao;

import cn.edu.nefu.gdms.biz.TeacherBiz;
import cn.edu.nefu.gdms.model.TopicPO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by dingyunxiang on 16/12/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TeacherBizTest {
    @Autowired
    private TeacherBiz teacherBiz;

    @Test
    public void getAllTopics(){
        List<TopicPO> topicPOList = teacherBiz.getTopicsByTeaId(1L);
        System.out.println(topicPOList.size());
    }

}
