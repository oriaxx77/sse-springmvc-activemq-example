package com.oriaxx77.play.newsagency;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class NewsAgencyApplication {
	
	@Bean
	public Queue newsQueue(){
		return new ActiveMQQueue( "news.queue" );
	}
	
	public static void main(String[] args) {
		SpringApplication.run(NewsAgencyApplication.class, args);
	}
}

