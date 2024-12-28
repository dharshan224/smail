package com.jsp.shan;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Controller
public class MyController {
     @Autowired
    JavaMailSender mailSender;

    @Autowired
    TemplateEngine engine;

    @GetMapping("/")
    public String load(){
        return "email.html";
    }
     @PostMapping("/send")
    public String send(@RequestParam String to, @RequestParam String subject, @RequestParam String message) throws MessagingException, UnsupportedEncodingException {
        // logic for Sending email
        MimeMessage mimeMessage=mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(mimeMessage);

        helper.setFrom("gdharshan997@gmail","Hacker");
        helper.addTo(to);
        helper.setSubject(subject);
        Context context=new Context();
        context.setVariable("message", message);
        helper.setText(engine.process("s.html", context),true);

        mailSender.send(mimeMessage);
        return "redirect:/";
    }


}