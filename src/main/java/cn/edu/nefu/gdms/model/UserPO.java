package cn.edu.nefu.gdms.model;

import lombok.Data;

/**
 * Created by dingyunxiang on 16/12/5.
 */
@Data
public class UserPO {
    private Long id;
    private int typeId;
    private String username;
    private String password;
    private String phone;
    private String email;
    private Long tutorId = 0L;
    private Long createTime;
    private Long updateTime;
    private String name;
    private String classes = "";
    private int title = 0;
    private int years;
}
