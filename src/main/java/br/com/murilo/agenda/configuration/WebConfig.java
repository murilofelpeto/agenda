package br.com.murilo.agenda.configuration;

import br.com.murilo.agenda.converter.EventRequestToEventConverter;
import br.com.murilo.agenda.converter.EventToEventResponseConverter;
import br.com.murilo.agenda.converter.UserRequestToUserConverter;
import br.com.murilo.agenda.converter.UserToUserResponseConverter;
import br.com.murilo.agenda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.format.DateTimeFormatter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private UserService userService;

    @Override
    public void addFormatters(final FormatterRegistry registry) {
        registry.addConverter(new UserRequestToUserConverter());
        registry.addConverter(new UserToUserResponseConverter());
        registry.addConverter(new EventRequestToEventConverter(userService));
        registry.addConverter(new EventToEventResponseConverter());
    }
}
