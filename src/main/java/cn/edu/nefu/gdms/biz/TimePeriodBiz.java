package cn.edu.nefu.gdms.biz;

import cn.edu.nefu.gdms.dao.TimePeriodDao;
import cn.edu.nefu.gdms.model.TimePeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dingyunxiang on 16/12/18.
 */
@Service
public class TimePeriodBiz {
    @Autowired
    private TimePeriodDao timePeriodDao;


    public long insertTimePeriod(TimePeriod timePeriod) {
        return timePeriodDao.insert(timePeriod);
    }

    public boolean deleteTimePeriod(long id) {
        return timePeriodDao.delete(id) > 0;
    }

    public boolean update(TimePeriod timePeriod) {
        return timePeriodDao.update(timePeriod) > 0;
    }
}
