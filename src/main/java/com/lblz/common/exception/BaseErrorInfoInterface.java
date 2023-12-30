package com.lblz.common.exception;

/**
 * @author lblz
 * @description 自定义的错误描述枚举类需实现
 * @date 2022/4/3 15:14
 */
public interface BaseErrorInfoInterface {
    /**
       返回结果状态
     */
    String getResultCode();

    /**
        返回结果消息
    */
    String getResultMessage();
}
