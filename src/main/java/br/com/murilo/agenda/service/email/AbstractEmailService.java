package br.com.murilo.agenda.service.email;

import br.com.murilo.agenda.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.thymeleaf.context.Context;
import org.thymeleaf.TemplateEngine;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine template;

    @Value("${default.sender}")
    private String sender;


    @Override
    public void sendEventNotification(final Event event, final String subject) {
        try {
            sendHtmlEmail(prepareMessage(event, subject));
        }catch (MessagingException ex) {

        }
    }

    private MimeMessage prepareMessage(final Event event, final String subject) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(event.getGuestsEmails().toArray(new String[event.getGuests().size()]));
        helper.setFrom(sender);
        helper.setSubject(subject);
        helper.setSentDate(new Date(System.currentTimeMillis()));
        helper.setText(htmlFromTemplateUser(event), true);
        return message;
    }

    private String htmlFromTemplateUser(final Event event) {
        String eventUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/event/").path(event.getId()).toUriString();
        Context context = new Context();
        context.setVariable("event", event);
        context.setVariable("organizer", event.getOrganizer().getUser().getName());
        context.setVariable("eventUrl", eventUrl);
        return template.process("event/EventEmail", context);
    }
}
