package com.example.demo.activemq;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class QueueProducer {

    @Autowired
    JmsTemplate jmsTemplate;

    @Value("${active-mq.queue}")
    private String queue;

    public void sendMessage(String message){
        try{
            log.info("Attempting Send message to Queue: "+ queue);
//            jmsTemplate.convertAndSend(queue, message);
//			TextMessage txtMessage = messageCreator.createTextMessage(activeMQDTO.getMessage());
//            jmsTemplate.send(message, null);
            
			jmsTemplate.send(queue, messageCreator -> {
				TextMessage txtMessage = messageCreator.createTextMessage(message);
				txtMessage.setJMSCorrelationID("ID:"+message);

				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
				String formatDateTime = now.format(formatter);
				log.info("message id: " + "ID:"+message);
				log.info("message pushed date time: " + formatDateTime);

				txtMessage.setStringProperty("sendDate", formatDateTime);
				return txtMessage;
			});
        } catch(Exception e){
            log.error("Recieved Exception during send Message: ", e);
        }
    }
}
