package demo;

import org.hornetq.jms.server.config.JMSQueueConfiguration;
import org.hornetq.jms.server.config.impl.JMSQueueConfigurationImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.data.rest.core.event.AfterCreateEvent;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public JMSQueueConfiguration bookOrder() {
		return new JMSQueueConfigurationImpl("bookOrder", null, false);
	}

	@Bean
	public JMSQueueConfiguration bookOrderStatus() {
		return new JMSQueueConfigurationImpl("bookOrderStatus", null, false);
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
