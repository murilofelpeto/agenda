package br.com.murilo.agenda.configuration;

import br.com.murilo.agenda.converter.RoleRequestToRoleConverter;
import br.com.murilo.agenda.converter.UserDTOToUserConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(final FormatterRegistry registry) {
        registry.addConverter(new UserDTOToUserConverter());
        registry.addConverter(new RoleRequestToRoleConverter());
    }
}
