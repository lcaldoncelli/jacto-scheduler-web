package br.com.jacto.schedulerservice;

import br.com.jacto.schedulerservice.configuration.SchedulerServiceConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@Slf4j
public class SchedulerServiceApplication {
	@Autowired
	SchedulerServiceConfiguration schedulerServiceConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(SchedulerServiceApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void onApplicationReady() {
		if (schedulerServiceConfiguration.isDbInit()) {
			log.info("Add default initial data");
		} else {
			log.info("Don't add default initial data");
		}
	}
}
