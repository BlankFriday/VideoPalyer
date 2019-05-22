package com.example.mylibrary.bean;

public class ApiException extends Throwable{
    public String error;
    public int code;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ApiException() {

    }

    public ApiException(String error, int code) {

        this.error = error;
        this.code = code;
    }
}
