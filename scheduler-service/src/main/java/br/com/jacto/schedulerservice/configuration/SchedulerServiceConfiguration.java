package br.com.jacto.schedulerservice.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class SchedulerServiceConfiguration {
    @Value("${app.db.init:#{false}}")
    private boolean dbInit;

    @Value("${app.postalCode.wsUrl}")
    private String postalCodeWsUrl;
}
