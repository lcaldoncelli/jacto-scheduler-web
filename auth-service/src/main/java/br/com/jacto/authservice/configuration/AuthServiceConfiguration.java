package br.com.jacto.authservice.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class AuthServiceConfiguration {
    @Value("${app.db.init:#{false}}")
    private boolean dbInit;
}
