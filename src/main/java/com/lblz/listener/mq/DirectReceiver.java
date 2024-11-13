package com.lblz.listener.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author lblz
 * @description mq的监听器 监听一对一,或者一对多
 * @date 2022/4/10 13:11
 */
@Component
@RabbitListener(queues = "TestDirectQueue")
public class DirectReceiver {

    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("第一个 DirectReceiver消费者收到消息  : " + testMessage.toString());
    }

}
