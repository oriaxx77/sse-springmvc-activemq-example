package com.oriaxx77.play.newsagency.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationEventListener {
	
	private NewsProducer newsProducer;
	
	@Autowired
	public ApplicationEventListener( NewsProducer newsProducer ){
		this.newsProducer = newsProducer;
	}
	
	@EventListener
	public void onApplicationReady( ApplicationReadyEvent event ){
		newsProducer.startSendingRandomNews();
	}
}

