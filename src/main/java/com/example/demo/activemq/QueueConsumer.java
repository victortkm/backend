package com.example.demo.activemq;

import org.springframework.jms.annotation.JmsListener;
import lombok.extern.slf4j.Slf4j;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@Slf4j
public class QueueConsumer implements MessageListener {


    @Override
    @JmsListener(destination = "${active-mq.topic}")
    public void onMessage(Message message) {
        try{
//            ObjectMessage objectMessage = (ObjectMessage)message;
//            String employee = (String)objectMessage.getObject();
//            //do additional processing
//           log.info("Received Message: "+ employee.toString());
        } catch(Exception e) {
          log.error("Received Exception : "+ e);
        }

    }
}
