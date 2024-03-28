package com.ecommercebackend.onlineshoping_backend.api.Sevices;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ecommercebackend.onlineshoping_backend.Exception.EmailFailureException;
import com.ecommercebackend.onlineshoping_backend.Models.VerificationToken;

@Service
public class EmailService {
    @Value("${email.from}")
    private String fromAdderss;
    @Value("${app.frontend.url}")
    private String url;

    private JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    private SimpleMailMessage makeMailMessage(){
        SimpleMailMessage simpleMailMessage =new SimpleMailMessage();
        simpleMailMessage.setFrom(fromAdderss);
        return simpleMailMessage;
    }
    public void sendVerificationEmail(VerificationToken token) throws EmailFailureException{
        SimpleMailMessage message = makeMailMessage();
        message.setTo(token.getUser().getEmail());
        message.setSubject("Verify Your Email");
        message.setText("Please follow this Link.\n" + url +"/auth/verify?token="+token.getToken());
        try{
            javaMailSender.send(message);
        }catch (MailException ex){
            throw new EmailFailureException();
        }
    }
    
}
