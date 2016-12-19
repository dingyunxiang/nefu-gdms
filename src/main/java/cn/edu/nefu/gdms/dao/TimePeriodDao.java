package cn.edu.nefu.gdms.dao;

import cn.edu.nefu.gdms.model.TimePeriod;

/**
 * Created by dingyunxiang on 16/12/12.
 */
public interface TimePeriodDao {
    public int insert(TimePeriod timePeriod);

    public int update(TimePeriod timePeriod);

    public int delete(long id);

    public TimePeriod get(long id);
}
