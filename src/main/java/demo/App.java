package demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.data.rest.core.event.AfterCreateEvent;
import org.springframework.stereotype.Service;

@SpringBootApplication
@EnableCaching
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public Queue bookOrder() {
		return new Queue("bookOrder");
	}

	@Bean
	public Queue bookOrderStatus() {
		return new Queue("bookOrderStatus");
	}

	@Service
	static class EntityCreatedHandler {

		private static final Logger logger = LoggerFactory.getLogger(EntityCreatedHandler.class);

		@EventListener
		public void handleEntityCreated(AfterCreateEvent event) {
			logger.info("Created " + event.getSource());
		}

	}

}
