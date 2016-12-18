package cn.edu.nefu.gdms.dao;

import cn.edu.nefu.gdms.model.Mark;
import cn.edu.nefu.gdms.model.TopicPO;

import java.util.List;

/**
 * Created by dingyunxiang on 16/12/12.
 */
public interface MarkDao {
    public int insert(Mark mark);

    public int update(Mark mark);

    public int delete(long id);

    public Mark get(long id);


}
