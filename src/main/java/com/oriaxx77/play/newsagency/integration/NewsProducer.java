package com.oriaxx77.play.newsagency.integration;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class NewsProducer {
	
	private JmsMessagingTemplate jmsMessagingTemplate;
	private Queue newsQueue;
	ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
	
	@Autowired
	public NewsProducer( JmsMessagingTemplate jmsMessagingTemplate,
						 Queue newsQueue) {
		this.jmsMessagingTemplate = jmsMessagingTemplate;
		this.newsQueue = newsQueue;
	}
	
	public void sendRandomNews(){
		String news = "News at " + LocalTime.now().toString();
		jmsMessagingTemplate.convertAndSend( newsQueue, news );
	}
	
	public void startSendingRandomNews() {	
		scheduledExecutorService.scheduleWithFixedDelay(this::sendRandomNews, 0, 1000, TimeUnit.MILLISECONDS);
	}
}
