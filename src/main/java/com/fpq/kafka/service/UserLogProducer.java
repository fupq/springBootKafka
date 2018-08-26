/**
 * 
 */
package com.fpq.kafka.service;


import com.alibaba.fastjson.JSON;
import com.fpq.kafka.bean.UserLog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author fpq
 *
 */
@Component
public class UserLogProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;
 
    /**
     * 发送数据
     * @param userid
     */
    public void sendLog(String userid){
        UserLog userLog = new UserLog();
        userLog.setUsername("jhp");
        userLog.setUserid(userid);
        userLog.setState("0");
        System.err.println("发送用户日志数据:"+userLog);
        kafkaTemplate.send("user-log", JSON.toJSONString(userLog));
    }
}
