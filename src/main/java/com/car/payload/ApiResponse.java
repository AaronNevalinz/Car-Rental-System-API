package com.car.payload;


public class ApiResponse <T>{
    private int status_code;
    private String message;
    private T data;


    public ApiResponse(int status, String message, T data) {
        this.status_code = status;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(200, message, data);
    }

    public static <T> ApiResponse<T> error(String message, T data) {
        return new ApiResponse<>(404, message, data);
    }

    public int getStatus() {
        return status_code;
    }

    public void setStatus(int status) {
        this.status_code = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
