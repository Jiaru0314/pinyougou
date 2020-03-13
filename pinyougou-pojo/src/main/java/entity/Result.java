package entity;

import java.io.Serializable;

/**
 * @ClassName: Result
 * @description: 返回结果类
 * @author: XZQ
 * @create: 2020/2/6 11:10
 **/
public class Result implements Serializable {

    private boolean success;//是否成功
    private String message;//返回信息

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
