package com.joel.KwetterApp.mail;

import com.joel.KwetterApp.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Properties;

@Controller
public class MailController {

    @Bean
    private JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("kweetapps9@gmail.com");
        mailSender.setPassword("kwetter420");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    public String sendUserEmail(User user) {
        try {
            sendEmail(user);
            return "Email Sent!";
        }catch(Exception ex) {
            return "Error in sending email: "+ex;
        }
    }

    private void sendEmail(User user) throws Exception{

        JavaMailSender sender = this.getJavaMailSender();

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo("joelcastillog@gmail.com");
        helper.setText("Verify your account by going here: localhost:8081/user/verify/" + user.getToken().getToken());
        helper.setSubject("Verify your account");

        sender.send(message);
    }

}
