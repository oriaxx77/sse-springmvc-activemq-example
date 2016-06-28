package com.oriaxx77.play.newsagency.integration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Component
public class NewsConsumer {
	
	private NewsSubscriptions newsSubscriptions;
	
	@Autowired
	public NewsConsumer( NewsSubscriptions newsSubscriptions ){
		this.newsSubscriptions = newsSubscriptions;
	}
	
	@JmsListener(destination="news.queue")
	public void onReceiveNews(String news){
		newsSubscriptions.forEach( sseEmitter -> sendNews(sseEmitter,news));
	}
	
	private void sendNews( SseEmitter sseEmitter, String news ){
		try{ 
			sseEmitter.send( news );
		}catch( IOException ex ){
			throw new RuntimeException( ex );
		}
	}
}
