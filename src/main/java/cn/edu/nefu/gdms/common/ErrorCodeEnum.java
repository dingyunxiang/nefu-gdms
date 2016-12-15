package cn.edu.nefu.gdms.common;

import cn.edu.nefu.gdms.exception.ServiceException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dingyunxiang on 16/12/12.
 */
public enum ErrorCodeEnum {
    //-----------------1xxxx 系统级错误----------------
    OK(10000, "OK", "Success"),
    SERVER_ERROR(10001, "Server error", "系统错误"),
    PARAMS_ERROR(10002, "Param error, see doc for more info", "参数错误，请参考API文档"),
    TARGET_NO_EXIST(10003, "Target No Exist", "目标不存在"),
    CALL_BSERVICE_ERROR(10004, "Call BService Error", "调用B端服务失败"),
    DUPLICATED_INSTRUCTION_ID(10005, "Duplicated instruction id", "重复的事务流水号"),
    SIGN_VERIFY_FAIL(10006, "Sign verify fail", "签名验证失败"),
    ENCRYPT_FAIL(10007, "encrypt fail", "加密失败"),
    PERMISSION_DENIED(10008, "permission denied", "权限不足"),
    DECRYPT_FAIL(10009, "decrypt fail", "解密失败"),
    DUPLICATED_REQUEST(100010, "duplicated request", "重复请求"),
    BSERVICE_DELAY(100011, "bservice delay", "B端服务延迟"),
    RELECT_ERROR(100012, "excel reflect error", "excel反射错误"),
    FILE_REPEAT(100013, "file repeat", "file已存在"),

    //----------------3xxxx Bussiness Errors ---------------
    ROLE_NOT_STU(30001, "Role not student", "角色不为学生或者未选导师"),
    STU_NOT_TUTOR(30002, "Student not in teacher", "此题目不是你导师的题目"),
    TEA_NUM_NOT_ENOUGH(30003, "teacher is less", "老师数量较少"),
    STU_BE_CHOOSE(30004, "Student has been choose", "该学生已被选择"),
    TOPIC_BE_CHOOSE(30004, "Topic has been choose", "该题目已被选择");


    public int errorCode;
    public String error;
    public String errorText;


    private ErrorCodeEnum(int errorCode, String error, String errorText) {
        this.errorCode = errorCode;
        this.error = error;
        this.errorText = errorText;
    }

    private static Map<Integer, ErrorCodeEnum> errorCodeEnums = new HashMap<Integer, ErrorCodeEnum>();;

    static {
        for (ErrorCodeEnum errorCodeEnum : ErrorCodeEnum.values()) {
            if (errorCodeEnums.containsKey(errorCodeEnum.errorCode)) {
                //填写了 同样errorCode的 ErrorCodeEnum
                throw new ServiceException(ErrorCodeEnum.PARAMS_ERROR);
            }
            errorCodeEnums.put(errorCodeEnum.errorCode, errorCodeEnum);
        }
    }

    public static ErrorCodeEnum getErrorCodeEnumFromId(int code) {
        return errorCodeEnums.get(code);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public java.lang.String getError() {
        return error;
    }

    public java.lang.String getErrorText() {
        return errorText;
    }

    @Override
    public String toString() {
        return "ErrorCodeEnum{" +
                "errorCode=" + errorCode +
                ", error='" + error + '\'' +
                ", errorText='" + errorText + '\'' +
                '}';
    }
}
