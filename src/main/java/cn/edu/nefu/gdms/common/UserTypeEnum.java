package cn.edu.nefu.gdms.common;

/**
 * Created by dingyunxiang on 16/12/12.
 */
public enum UserTypeEnum {
    STUDENT(1),
    TEACHER(2),
    ADMIN(3);


    private int value;

    UserTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
