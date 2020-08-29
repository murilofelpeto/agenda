package br.com.murilo.agenda.configuration;

import br.com.murilo.agenda.service.email.EmailService;
import br.com.murilo.agenda.service.email.SmtpEmailServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("application.properties")
public class EmailConfiguration {

    @Bean
    public EmailService emailService() {
        return new SmtpEmailServices();
    }
}
