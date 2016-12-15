package cn.edu.nefu.gdms.common;

/**
 * Created by dingyunxiang on 16/12/15.
 */
public enum TopicStatusEnum {
    PREPARE(1),
    CHOOSE(2),
    UPLOAD(3);


    private int value;

    TopicStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
