/**
 * 
 */
package com.fpq.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author fpq
 *
 */
@RestController
public class KafkaController {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 引入springBoot集成kafka开发的kafkaTemplate插件，在配置文件中按照要求配置及可对kafka发送和接收消息
	 */
	@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
	
	
	/**
	 * 向kafka发送消息
	 * @param message
	 * @return
	 * http://127.0.0.1:8085/kafkaSend?message=springBoot集成kafka发送消息
	 */
	@RequestMapping("/kafkaSend")
	public String kafkaSend(String message) {
		String info = "";
		ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send("test", "key", message);
    	boolean send = listenableFuture.isDone();
    	if(send == false) {
    		info = "使用kafka发送消息‘"+ message +"’成功.";
    	}else {
    		info = "kafka发送消息失败!";
    	}
		return info;
	}
	
	
	 /**
     * @Description:kafka生产者发送消息
     * @Title: sendKafka 
     * @param request
     * @param response
     * @return  
     * @throws
     * http://127.0.0.1:8085/send
     * http://127.0.0.1:8085/send
     */
    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String sendKafka(HttpServletRequest request, HttpServletResponse response) {
    	String message = "";//request.getParameter("fpq在springBoot中集成kafka来发送消息，message");
        try {
            logger.info("********************** 开始使用kafka发送消息！" );
            String result = "";
            for(int i=1;i<=50;i++) {
            	message = "fpq在springBoot中集成kafka来发送消息______"+i;
            	ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send("test", "key", message);
            	boolean send = listenableFuture.isDone();
            	if(send == true) {
            		result = "使用kafka发送消息‘"+ message +"’成功.";
            	}else {
            		result = "kafka发送消息失败!";
            	}
            	logger.info(result);
            }
            return "kafka发送消息完毕!";//new Response(ResultCode.SUCCESS, "发送kafka成功", null);
        } catch (Exception e) {
            logger.error("kafka发送消息失败!", e);
            return "kafka发送消息失败!";//new Response(ResultCode.EXCEPTION, "发送kafka失败", null);
        }
    }
}
