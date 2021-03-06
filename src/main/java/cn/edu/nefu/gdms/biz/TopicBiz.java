package cn.edu.nefu.gdms.biz;

import cn.edu.nefu.gdms.dao.TopicDao;
import cn.edu.nefu.gdms.model.TopicPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dingyunxiang on 16/12/12.
 */
@Service
public class TopicBiz {
    @Autowired
    private TopicDao topicDao;

    public long insert(TopicPO topicPO) {
        topicPO.setCreateTime(System.currentTimeMillis());
        topicPO.setCreateTime(topicPO.getUpdateTime());
        return topicDao.insert(topicPO);
    }

    public boolean update(TopicPO topicPO) {
        topicPO.setUpdateTime(System.currentTimeMillis());
        return topicDao.update(topicPO) > 0;
    }

    public boolean delete(long topicId) {
        return topicDao.delete(topicId) > 0;
    }

    public List<TopicPO> getTopicsByTeaId(long tutorId) {
        return topicDao.findTopicListByTutorId(tutorId);
    }

    public TopicPO getTopicByStuId(long stuId) {
        return topicDao.findTopicByStuId(stuId);
    }

    public TopicPO get(long id) {
        return topicDao.get(id);
    }
}
