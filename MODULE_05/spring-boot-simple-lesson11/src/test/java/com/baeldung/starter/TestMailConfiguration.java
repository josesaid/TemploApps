package com.baeldung.starter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class TestMailConfiguration {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("localhost");
        mailSender.setPort(8025);
        mailSender.setProtocol("smtp");

        Properties properties = new Properties();
        properties.setProperty("mail.debug", "true");
        mailSender.setJavaMailProperties(properties);

        return mailSender;
    }

}