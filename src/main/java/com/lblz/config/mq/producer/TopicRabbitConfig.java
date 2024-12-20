package com.lblz.config.mq.producer;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lblz
 * @description Exchange有常见以下3种类型：(Exchange（交换机）只负责转发消息，不具备存储消息的能力，因此如果没有任何队列与 Exchange 绑定，或者没有符合路由规则的队列，那么消息会丢失！)
 *                  Fanout：广播，将消息交给所有绑定到交换机的队列
 *                  Direct：定向，把消息交给符合指定routing key 的队列
 *                  Topic：通配符，把消息交给符合routing pattern（路由模式） 的队列
 *
 *              主题交换机，这个交换机其实跟直连交换机流程差不多，但是它的特点就是在它的路由键和绑定键之间是有规则的。
 *              简单地介绍下规则：
 *                   *  (星号) 用来表示一个单词 (必须出现的)
 *                   #  (井号) 用来表示任意数量（零个或多个）单词
 *                   通配的绑定键是跟队列进行绑定的，举个小例子
 *                   队列Q1 绑定键为 *.TT.*          队列Q2绑定键为  TT.#
 *                   如果一条消息携带的路由键为 A.TT.B，那么队列Q1将会收到；
 *                   如果一条消息携带的路由键为TT.AA.BB，那么队列Q2将会收到；
 * @date 2022/4/10 13:14
 */
@Configuration
public class TopicRabbitConfig {
    //绑定键
    public final static String man = "topic.man";
    public final static String woman = "topic.woman";

    @Bean // 有两个队列
    public Queue firstQueue() {
        return new Queue(TopicRabbitConfig.man);
    }

    @Bean
    public Queue secondQueue() {
        return new Queue(TopicRabbitConfig.woman);
    }

    @Bean // 主题交换机
    TopicExchange exchange() {
        return new TopicExchange("topicExchange");
    }


    //将firstQueue和topicExchange绑定,而且绑定的routingKey值为topic.man
    //这样只要是消息携带的路由键是topic.man,才会分发到该队列
    @Bean
    Binding bindingExchangeMessage() {
        return BindingBuilder.bind(firstQueue()).to(exchange()).with(man);
    }

    // 将secondQueue和topicExchange绑定,而且绑定的键值为用上通配路由键规则topic.#
    // 这样只要是消息携带的路由键是以topic.开头,都会分发到该队列
    @Bean
    Binding bindingExchangeMessage2() {
        return BindingBuilder.bind(secondQueue()).to(exchange()).with("topic.#");
    }
}
