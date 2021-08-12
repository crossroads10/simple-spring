package com.wy.spring.response;

/**
 * @Classname MyResponse
 * @Description TODO
 * @Date 2021/8/10 11:35 下午
 * @Company 杭州光云科技有限公司
 * @Author wy
 */

public class MyResponse {
    private String status;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MyResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
