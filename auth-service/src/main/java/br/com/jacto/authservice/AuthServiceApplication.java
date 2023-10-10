package br.com.jacto.authservice;

import br.com.jacto.authservice.configuration.AuthServiceConfiguration;
import br.com.jacto.authservice.entity.UserEntity;
import br.com.jacto.authservice.exceptions.AuthException;
import br.com.jacto.authservice.model.CreateUserModel;
import br.com.jacto.authservice.model.UserModel;
import br.com.jacto.authservice.model.UserRole;
import br.com.jacto.authservice.repository.UserRepository;
import br.com.jacto.authservice.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@Slf4j
public class AuthServiceApplication {
	@Autowired
	AuthServiceConfiguration authServiceConfiguration;

	@Autowired
	AuthService authService;

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}


	@EventListener(ApplicationReadyEvent.class)
	public void onApplicationReady() throws AuthException {
		if (authServiceConfiguration.isDbInit()) {
			CreateUserModel user = CreateUserModel.builder()
					.email("string")
					.role(UserRole.TECHNICIAN)
					.phoneNumber("+5541999757451")
					.name("Lucas Caldoncelli Rodrigues")
					.password("string")
					.build();
			authService.create(user);
		}
	}
}
