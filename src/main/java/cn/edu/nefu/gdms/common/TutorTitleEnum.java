package cn.edu.nefu.gdms.common;

/**
 * Created by dingyunxiang on 16/12/14.
 */
public enum TutorTitleEnum {
    TEACHER(1),
    ASSCOIATE_PROFESSOR(2),
    PROFESSOR(3);


    private int value;

    TutorTitleEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
