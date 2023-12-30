package com.lblz.common.http;

import com.lblz.common.exception.BaseErrorInfoInterface;
import com.lblz.entity.enums.ApiHttpStateCodeEnums;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lblz
 * @description 接口结果返回
 * @date 2022/3/30 21:02
 */
@Data
public class ApiHttpResult<T> implements Serializable {
    /**
     *  返回的数据,类型根据泛型去实现的
     */
    private T data;

    /**
     *   返回的消息信息
     */
    private String message;

    /**
     *  返回的状态码
     */
    private String code;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ApiHttpResult(T data, String message, String code) {
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public ApiHttpResult(T data) {
        this.data = data;
    }

    public ApiHttpResult() {

    }

    public ApiHttpResult<T> success(Object data){
        return new ApiHttpResult(data, ApiHttpStateCodeEnums.SUCCESS.getMessage(),ApiHttpStateCodeEnums.SUCCESS.getCode());
    }

    public ApiHttpResult<T> success(){
        return new ApiHttpResult(null,ApiHttpStateCodeEnums.SUCCESS.getMessage(),ApiHttpStateCodeEnums.SUCCESS.getCode());
    }

    public static ApiHttpResult ok(){
        return new ApiHttpResult(null, ApiHttpStateCodeEnums.SUCCESS.getMessage(), ApiHttpStateCodeEnums.SUCCESS.getCode());
    }

    public static ApiHttpResult success(String message,String code){
        return new ApiHttpResult(null, message, code);
    }
    public static ApiHttpResult fail(){
        return new ApiHttpResult(null, ApiHttpStateCodeEnums.FAIL.getMessage(), ApiHttpStateCodeEnums.FAIL.getCode());
    }

    public static ApiHttpResult fail(String message,String code){
        return new ApiHttpResult(null, message, code);
    }

    public static ApiHttpResult fail(BaseErrorInfoInterface infoInterface){
        return new ApiHttpResult(null, infoInterface.getResultCode(), infoInterface.getResultMessage());
    }
}
