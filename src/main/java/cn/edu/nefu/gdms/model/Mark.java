package cn.edu.nefu.gdms.model;

import lombok.Data;

/**
 * Created by dingyunxiang on 16/12/18.
 */
@Data
public class Mark {
    private long id;
    private long tutorId;
    private long stuId;
    private long topicId;
    private int grade;
    private String discuss;
}
