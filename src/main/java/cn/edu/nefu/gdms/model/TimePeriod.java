package cn.edu.nefu.gdms.model;

import lombok.Data;

/**
 * Created by dingyunxiang on 16/12/13.
 */
@Data
public class TimePeriod {
    private long id;
    private String name;
    private long beginTime;
    private long endTime;
}
