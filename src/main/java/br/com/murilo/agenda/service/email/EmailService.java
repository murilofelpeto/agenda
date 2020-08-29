package br.com.murilo.agenda.service.email;

import br.com.murilo.agenda.entity.Event;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    void sendEventNotification(Event event, String subject);
    void sendHtmlEmail(MimeMessage message);
}