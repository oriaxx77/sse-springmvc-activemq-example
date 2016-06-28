package com.oriaxx77.play.newsagency.integration;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Component
public class NewsSubscriptions {
	
	private CopyOnWriteArrayList<SseEmitter> sseEmitters = new CopyOnWriteArrayList<>();
	
	public SseEmitter subscribe(){
		SseEmitter sseEmitter = new SseEmitter();
		sseEmitter.onCompletion( () -> sseEmitters.remove( sseEmitter ));
		sseEmitters.add( sseEmitter );
		return sseEmitter;
	}
	
	public void forEach( Consumer<? super SseEmitter> action ){
		sseEmitters.forEach(action);
	}
}
