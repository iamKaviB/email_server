package com.email.emailserver;

import com.email.emailserver.payload.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
public class EmailController {
    @Autowired
    private EmailMessage emailMessage;

    @RequestMapping("/api/v1/email/send")
    public ResponseEntity<String> sendMail(@RequestBody EmailRequest request) throws MessagingException {

        try {
            emailMessage.send(request.getEmail(), request.getSubject(), request.getMsg());
            return new ResponseEntity<>("SEND", HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>("FAILED", HttpStatus.FAILED_DEPENDENCY);

        }
    }
}
