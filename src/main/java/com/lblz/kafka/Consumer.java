package com.lblz.kafka;




import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
/**
 * <p>
 *
 * </p>
 *
 * @author Jy
 * @date 2024/11/13
 */
public class Consumer {

    static Logger log = Logger.getLogger(Producer.class);

    private static final List<String> TOPIC = Arrays.asList("milo2","test");
    private static final String BROKER_LIST = "192.168.102.11:9092";
    private static KafkaConsumer<String,String> consumer = null;

    static {
        Properties configs = initConfig();
        consumer = new KafkaConsumer<String, String>(configs);
        consumer.subscribe(TOPIC); //消息订阅
    }

    private static Properties initConfig(){
        Properties properties = new Properties();
        properties.put("bootstrap.servers",BROKER_LIST);
        properties.put("group.id","0");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("enable.auto.commit", "true");
        properties.setProperty("auto.offset.reset", "earliest");
        return properties;
    }


    public static void main(String[] args) {
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(10);
//            for (ConsumerRecord<String, String> record : records) { //不区分主题、分区
//                log.info(record);
//            }
            //按主题消费数据
            Iterable<ConsumerRecord<String,String>> iter = records.records("test");
            iter.forEach(p->{
                System.out.println("key===="+p.key()+"Value===="+p.value());
            });
        }
    }
}