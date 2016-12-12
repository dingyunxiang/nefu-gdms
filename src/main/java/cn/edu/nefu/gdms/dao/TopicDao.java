package cn.edu.nefu.gdms.dao;

import cn.edu.nefu.gdms.model.TopicPO;

import java.util.List;

/**
 * Created by dingyunxiang on 16/12/12.
 */
public interface TopicDao {
    public int insert(TopicPO topicPO);

    public int update(TopicPO topicPO);

    public int delete(TopicPO topicPO);

    public List<TopicPO> findTopicListByTutorId(long tutorId);

    public TopicPO findTopicByStuId(long stuId);

    public TopicPO get(long id);


}
