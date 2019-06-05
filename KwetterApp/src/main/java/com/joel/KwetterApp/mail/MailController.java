package com.joel.KwetterApp.mail;

import com.joel.KwetterApp.model.User;
import org.springframework.stereotype.Controller;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MailController {

    @Autowired
    private JavaMailSender sender;

    public String sendUserEmail(User user) {
        try {
            sendEmail(user);
            return "Email Sent!";
        }catch(Exception ex) {
            return "Error in sending email: "+ex;
        }
    }

    private void sendEmail(User user) throws Exception{
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo("joel@castl.nl");
        helper.setText("Verify your account by going here: localhost:8081/" + user.getToken().getToken());
        helper.setSubject("Verify your account");

        sender.send(message);
    }

}
