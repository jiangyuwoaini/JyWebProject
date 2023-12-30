package com.lblz.common.exception;

import com.lblz.common.http.ApiHttpResult;
import com.lblz.entity.enums.ApiHttpStateCodeEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * @author lblz
 * @description 全局异常类
 * @date 2022/4/3 15:27
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理自定义的业务异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = MyExceptionMessage.class)
    @ResponseBody
    public ApiHttpResult bizExceptionHandler(HttpServletRequest req, MyExceptionMessage e){
        logger.error("发生业务异常！原因是：{}",e.getErrorMsg());
        return ApiHttpResult.fail(e.getErrorCode(),e.getErrorMsg());
    }

    /**
     * 处理空指针的异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =NullPointerException.class)
    @ResponseBody
    public ApiHttpResult exceptionHandler(HttpServletRequest req, NullPointerException e){
        logger.error("发生空指针异常！原因是:",e);
        return ApiHttpResult.fail(ApiHttpStateCodeEnums.FAIL);
    }

    /**
     * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public ApiHttpResult exceptionHandler(HttpServletRequest req, Exception e){
        logger.error("未知异常！原因是:",e);
        return ApiHttpResult.fail(ApiHttpStateCodeEnums.FAIL);
    }
}
