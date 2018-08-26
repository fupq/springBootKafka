/**
 * 
 */
package com.fpq.kafka;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fpq.kafka.service.UserLogProducer;

import javax.annotation.PostConstruct;

/**
 * @author fpq
 *
 */
@SpringBootApplication
public class KafkaApplicationStart {

    @Autowired
    private UserLogProducer kafkaSender;
    @PostConstruct
    public void init(){
        for (int i = 0; i < 10; i++) {
            //������Ϣ�������е���Ϣ���ͷ���
            kafkaSender.sendLog(String.valueOf(i));
        }
        kafkaSender.sendLog("******************************* 消息发送完毕！********************************************************");
    }
 
    public static void main(String[] args) {
        SpringApplication.run(KafkaApplicationStart.class,args);
    }

}
