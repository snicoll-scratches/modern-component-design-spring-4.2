package demo;

import javax.cache.configuration.MutableConfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
	public JCacheManagerCustomizer cacheManagerCustomizer() {
		return cm -> cm.createCache("books",
				new MutableConfiguration<>().setStatisticsEnabled(true));
	}

	interface MessagingConfig<T> {

		@Bean
		default T bookOrder() {
			return createQueue("bookOrder");
		}

		@Bean
		default T bookOrderStatus() {
			return createQueue("bookOrderStatus");
		}

		T createQueue(String name);

	}

	@Configuration
	static class RabbitConfig implements MessagingConfig<Queue> {

		@Override
		public Queue createQueue(String name) {
			return new Queue(name);
		}
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
