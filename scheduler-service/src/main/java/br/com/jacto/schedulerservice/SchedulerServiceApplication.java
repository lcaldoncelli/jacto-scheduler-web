package br.com.jacto.schedulerservice;

import br.com.jacto.schedulerservice.configuration.SchedulerServiceConfiguration;
import br.com.jacto.schedulerservice.entity.AddressEntity;
import br.com.jacto.schedulerservice.entity.VisitScheduleEntity;
import br.com.jacto.schedulerservice.repository.AddressRepository;
import br.com.jacto.schedulerservice.repository.VisitScheduleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.time.LocalDateTime;

@SpringBootApplication
@Slf4j
public class SchedulerServiceApplication {
	@Autowired
	SchedulerServiceConfiguration schedulerServiceConfiguration;

	@Autowired
	AddressRepository addressRepository;
	@Autowired
	VisitScheduleRepository visitScheduleRepository;

	public static void main(String[] args) {
		SpringApplication.run(SchedulerServiceApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void onApplicationReady() {
		if (schedulerServiceConfiguration.isDbInit()) {
			log.info("Add default initial data");
			AddressEntity address = AddressEntity.builder()
					.city("Curitiba")
					.state("PR")
					.postalCode(81820020)
					.street("Rua Jose Da silva")
					.number(123)
					.district("Pinheirinho")
					.complement("Casa 12")
					.build();

			VisitScheduleEntity visitSchedule = VisitScheduleEntity.builder()
					.userId(1L)
					.address(address)
					.creationDate(LocalDateTime.now())
					.serviceDescription("Máquina danificada")
					.startDate(LocalDateTime.now().plusDays(5))
					.endDate(LocalDateTime.now().plusDays(5).plusHours(2))
					.build();
			visitScheduleRepository.save(visitSchedule);


			AddressEntity address2 = AddressEntity.builder()
					.city("São Paulo")
					.state("SP")
					.postalCode(80000440)
					.street("Rua da sé")
					.number(976)
					.district("Centro")
					.complement("Fundos")
					.build();
			VisitScheduleEntity visitSchedule2 = VisitScheduleEntity.builder()
					.userId(1L)
					.address(address2)
					.creationDate(LocalDateTime.now().plusSeconds(2))
					.serviceDescription("Equipamento Serie 124919 não comunica com a central")
					.startDate(LocalDateTime.now().plusDays(6))
					.endDate(LocalDateTime.now().plusDays(6).plusHours(3))
					.build();
			visitScheduleRepository.save(visitSchedule2);

		} else {
			log.info("Don't add default initial data");
		}
	}
}
