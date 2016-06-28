package com.oriaxx77.play.newsagency.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.oriaxx77.play.newsagency.integration.NewsSubscriptions;

@RestController()
@RequestMapping("/news")
public class NewsResource {
	
	private NewsSubscriptions newsSubscriptions;
	
	@Autowired
	public NewsResource( NewsSubscriptions newsSubscriptions ){
		this.newsSubscriptions = newsSubscriptions;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public SseEmitter subscribe(){
		return newsSubscriptions.subscribe();
	}
	
	
	
	
}
