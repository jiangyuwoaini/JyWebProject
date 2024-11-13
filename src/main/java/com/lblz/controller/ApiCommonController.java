package com.lblz.controller;

import com.lblz.common.http.ApiHttpResult;
import com.lblz.common.utils.RedisUtils;
import com.lblz.entity.enums.ApiHttpStateCodeEnums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Random;

/**
 * @author lblz
 * @description
 * @date 2022/3/30 21:28
 */
@RestController
@RequestMapping("/api/common")
public class ApiCommonController {

    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping(value = "/create/random",method = {RequestMethod.GET})
    public ApiHttpResult<List<String>> createRandom(HttpServletRequest request) {
        request.getServletContext();
        Random random = new Random(1024);
        List<String> list = new ArrayList<>();
        int randomNumber = random.nextInt(47);
//        System.out.println(list.get(10000));
        for (int i = 0; i< randomNumber; i++) {
            list.add(random.nextInt(47) +"&*%");
        }
        //查询缓存中是否存在
        boolean hasKey = redisUtils.exists("hello");
        if(hasKey){
            Object object =  redisUtils.get("hello");
            list.add(object.toString());
        }
        return new ApiHttpResult<List<String>>().success(list);
    }

    @RequestMapping(value = "/test1",method = {RequestMethod.GET})
    public ApiHttpResult test1()  {
        return ApiHttpResult.success("请求成功,当前时间:\n"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), ApiHttpStateCodeEnums.SUCCESS.getMessage());
    }

}
