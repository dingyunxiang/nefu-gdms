package cn.edu.nefu.gdms.model;

import lombok.Data;

/**
 * Created by dingyunxiang on 16/12/12.
 */
@Data
public class TopicPO {
    private long id;
    private long tutorId;
    private long stuId;
    private int status;
    private String title;
    private String filePath;
    private long createTime;
    private long updateTime;
    private int years;
}
