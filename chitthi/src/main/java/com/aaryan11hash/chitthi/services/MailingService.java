package com.aaryan11hash.chitthi.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class MailingService {

    private final JavaMailSender mailSender;

    @Async("AsyncTaskExecutor")
    public void sendHackRXMail(String emailId){

        String BODY = "Dear User,"
                +"\n This is a test mail to hint that you have recently uploaded a scanned image/document with us,"
                +"\n plaase ignore this mail";

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("covidTrackerMailUtility@gmail.com");
        mailMessage.setTo(emailId);
        mailMessage.setText(BODY);
        mailMessage.setSubject("Upload Trigger message");
        mailSender.send(mailMessage);

    }
}
