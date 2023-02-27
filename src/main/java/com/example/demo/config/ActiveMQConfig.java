package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

@Slf4j
@Configuration
@EnableJms
public class ActiveMQConfig {

	@Value("${active-mq.broker-url}")
    private String BROKER_URL;

	@Value("${active-mq.concurrency.outward-queue}")
	private String DEMO_OUTWARD_QUEUE;

	@Value("${active-mq.concurrency.inward-queue}")
	private String DEMO_INWARD_QUEUE;

	@Bean
	public ConnectionFactory activeMQConnectionFactory() {
		ConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
		log.info(BROKER_URL); 
        log.info("------------- init factory ------------- ");
		return activeMQConnectionFactory;
	}
	
	@Bean
	public DefaultJmsListenerContainerFactory inwardDemoQueueJMSFactory() {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(activeMQConnectionFactory());
		factory.setAutoStartup(true);
		factory.setConcurrency(DEMO_INWARD_QUEUE);
		log.info("------------factory: " +DEMO_INWARD_QUEUE);
		return factory;
	}
	//endregion
	
}
