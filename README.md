# Server Sent Event example with Spring MVC and Rxjava
Example application for Server Sent Events using Spring MVC and ActiveMQ


##1. Functionality
The app is a webapp that enables the clients to subscribe to news. 


##2. Architecture

###Accessing the app (Rest Endpoints)
The app provides the `/news` REST endpoint with the `NewsResource` REST resource. The `NewsResource` is
implemented as a Spring MVC Controller and the   `NewsResource#subscribe()` method returns an `SseEmitter` SseEmitter that 
is used to send CoinMined events back to the client. The created `SseEmitter` is stored in the `NewsSubscriptions`.

###Sending news to the ActiveMQ broker
The app has a `NewsProducer` that sends a news message to an im-memory ActiveMQ message broker.
This job is started by the `AplplicationEventListener` right after the applications is started.

```Java
@Component
public class ApplicationEventListener {
...	
	@EventListener
	public void onApplicationReady( ApplicationReadyEvent event ){
		newsProducer.startSendingRandomNews();
	}
...
}
```
###Consuming ActiveMQ broker messages 
The app also has a `NewsConsumer` that has a @JmsListener annotated method to get notified about messages sent to
the ActiveMQ queue. It transforms the message and broadcasts it all subscribed clients.

```Java
@Component
public class NewsConsumer {	
...	
	@JmsListener(destination="news.queue")
	public void onReceiveNews(String news){
		newsSubscriptions.forEach( sseEmitter -> sendNews(sseEmitter,news));
	}
...	
}
```



##3. Building the app
Run the following command to create an eclipse project: gradle clean build cleanEclipse eclipse


##4. Running the app
1. Run the NewsApplication spring boot application.
2. Open your browser with the http://localhost:8080/news URL

##5. Open issues
1. Implement Unit testing
2. Implement Integration testing
3. Implement reactive UI
4. Create some nice UML diagrams
5. Create a News class
