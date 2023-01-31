package com.example.demo.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

@Slf4j
@Component
public class QueueConsumer {
	
//    @JmsListener(destination = "${active-mq.inward-queue}")
//	@JmsListener(destination = "${active-mq.inward-queue}", containerFactory = "InwardDemoQueueJMSFactory", id = "InwardDemoQueueJMSListener")
    public void consumeMessage() {
        try{

        	log.info("---------");
        	log.info("INWARD CONSENT MESSAGE: ");
        	log.info("---------");
            // Create a ConnectionFactory
        	ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

            // Create a Connection
            Connection connection = connectionFactory.createConnection();
            connection.start();

            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            Destination destination = session.createQueue("DEMO_INWARD_QUEUE");

            // Create a MessageConsumer from the Session to the Topic or Queue
            MessageConsumer consumer = session.createConsumer(destination);

            // Wait for a message
            Message message = consumer.receive(1000);

            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                log.info("Received msg: " + text);
                log.info("Received msg: " + message);
            } else {
                System.out.println("Received: " + message);
            }

            consumer.close();
            session.close();
            connection.close();
        } catch(Exception e) {
          log.error("Received Exception : "+ e);
        }

    }
    
//	@JmsListener(destination = "${active-mq.name.inward-queue}", containerFactory = "inwardDemoQueueJMSFactory" )
//    public void receiveMessage(final Message message) throws JMSException{
//        log.info("Received msg: " + message);
//        String messageData = null;
//        if(message instanceof TextMessage) {
//            TextMessage textMessage = (TextMessage)message;
//            messageData = textMessage.getText();
//        }
//    }
    
}
