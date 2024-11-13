package com.lblz.config.mq.producer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lblz
 * @description Ack消息确认回调函数
 * @date 2022/4/10 13:39
 */
@Configuration
public class RabbitMQAckConfig {


    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
       /*
        当将mandatory设置为false（默认值），如果RabbitMQ无法将消息路由，消息将会被静默丢弃，生产者不会收到通知。
        当设置mandatory为true时，意味着消息被视为"mandatory"，如果在发布消息时RabbitMQ无法将消息路由到任何队列（例如由于没有匹配的队列与指定的路由键），则代理将通过调用ReturnListener回调的returnedMessage方法将消息返回给生产者（发布者）。生产者可以根据需要适当地处理这个返回的消息，例如记录日志或执行某些恢复操作。
         */
        rabbitTemplate.setMandatory(true);
        // 消息发送到队列
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            System.out.println("ConfirmCallback:     "+"相关数据："+correlationData);
            System.out.println("ConfirmCallback:     "+"确认情况："+ack);
            System.out.println("ConfirmCallback:     "+"原因："+cause);
        });
        // 消息发送到交换机
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                System.out.println("ReturnCallback:     "+"消息："+message);
                System.out.println("ReturnCallback:     "+"回应码："+replyCode);
                System.out.println("ReturnCallback:     "+"回应信息："+replyText);
                System.out.println("ReturnCallback:     "+"交换机："+exchange);
                System.out.println("ReturnCallback:     "+"路由键："+routingKey);
            }
        });
        return rabbitTemplate;
    }

}