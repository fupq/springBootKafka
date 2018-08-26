/**
 * 
 */
package com.fpq.kafka.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
 
import java.util.Optional;

/**
 * @author fpq
 *
 */
@Component
@Slf4j
public class UserLogConsumer {

	/**
	 * ���ѻ�����ͨ��������ʵ�ֵ�,ֱ��ʹ��KafkaListener(topics = {"user-log"})ע��ӿ�,�����Ը���ָ��������������Ϣ�ļ���:
	 * @param consumerRecord
	 */
    @KafkaListener(topics = {"user-log"})
    public void consumer(ConsumerRecord<?,?> consumerRecord){
        //�ж��Ƿ�Ϊnull
        Optional<?> kafkaMessage = Optional.ofNullable(consumerRecord.value());
        System.out.println(">>>>>>>>>> record =" + kafkaMessage);
        if(kafkaMessage.isPresent()){
            //�õ�Optionalʵ���е�ֵ
            Object message = kafkaMessage.get();
            System.err.println("***********接收到的消息:"+message);
        }
    }
}
