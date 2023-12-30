package com.lblz.entity.enums;

import com.lblz.common.exception.BaseErrorInfoInterface;

/**
 * @author lblz
 * @description 状态码
 * @date 2022/3/30 21:05
 */
public enum ApiHttpStateCodeEnums implements BaseErrorInfoInterface {
    SUCCESS("200","成功！"),
    FAIL("400","失败！"),
    PARAMS_ERROR("400","参数异常！");

    private String code;

    private String message;

    ApiHttpStateCodeEnums(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String getResultCode() {
        return this.getCode();
    }

    @Override
    public String getResultMessage() {
        return this.getMessage();
    }
}
