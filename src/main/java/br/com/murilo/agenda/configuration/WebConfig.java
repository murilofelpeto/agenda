package br.com.murilo.agenda.configuration;

import br.com.murilo.agenda.converter.UserRequestToUserConverter;
import br.com.murilo.agenda.converter.UserToUserResponseConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(final FormatterRegistry registry) {
        registry.addConverter(new UserRequestToUserConverter());
        registry.addConverter(new UserToUserResponseConverter());
    }
}
