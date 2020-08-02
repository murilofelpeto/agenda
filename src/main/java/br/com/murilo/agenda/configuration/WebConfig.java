package br.com.murilo.agenda.configuration;

import br.com.murilo.agenda.converter.*;
import br.com.murilo.agenda.service.UploadFilesService;
import br.com.murilo.agenda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final UserService userService;
    private final ConversionService conversionService;
    private final UploadFilesService uploadFilesService;

    public WebConfig(@Autowired UserService userService,
                     @Autowired UploadFilesService uploadFilesService,
                     @Lazy ConversionService conversionService) {
        this.userService = userService;
        this.uploadFilesService = uploadFilesService;
        this.conversionService = conversionService;
    }

    @Override
    public void addFormatters(final FormatterRegistry registry) {
        registry.addConverter(new EventCreationRequestToEventConverter(userService));
        registry.addConverter(new EventRequestToEventConverter(conversionService));
        registry.addConverter(new EventToEventResponseConverter(userService));
        registry.addConverter(new MultiPartToPictureConverter(uploadFilesService));
        registry.addConverter(new PictureToPictureResponseConverter());
        registry.addConverter(new UserEventResponseToEventUserResponseConverter(userService));
        registry.addConverter(new UserRequestToUserConverter());
        registry.addConverter(new UserToUserResponseConverter(conversionService));
    }
}
