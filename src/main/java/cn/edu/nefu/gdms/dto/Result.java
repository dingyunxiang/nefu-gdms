package cn.edu.nefu.gdms.dto;

/**
 * Created by dingyunxiang on 16/12/12.
 */
public class Result<T> {
    private boolean success;
    private T data;
    private String error;

    public Result() {
    }

    public Result(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public Result(boolean success, T data) {

        this.success = success;
        this.data = data;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
