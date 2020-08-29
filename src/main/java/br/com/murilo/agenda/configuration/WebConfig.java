package br.com.murilo.agenda.configuration;

import br.com.murilo.agenda.converter.*;
import br.com.murilo.agenda.service.UploadFilesService;
import br.com.murilo.agenda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.FormatterRegistry;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

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

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource= new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages/validation/message");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean validator(MessageSource messageSource) {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource);
        return bean;
    }

    @Bean
    public LocaleResolver localeResolver(){
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(new Locale("pt", "BR"));
        return localeResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(localeChangeInterceptor());
    }
}
