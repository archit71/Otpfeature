package com.example.demo.service;

import com.twilio.Twilio;
import com.twilio.exception.TwilioException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SmsService {

    @Value("${twilio.accountSid}")
    private String accountSid;

    @Value("${twilio.authToken}")
    private String authToken;

    @Value("${twilio.phoneNumber}")
    private String twilioPhoneNumber;

    public void sendSms(String to, String message) {
        Twilio.init(accountSid, authToken);

        try {
            Message.creator(
                            new PhoneNumber(to),
                            new PhoneNumber(twilioPhoneNumber),
                            message)
                    .create();
        } catch (TwilioException e) {
            // Handle exception (e.g., log or throw a custom exception)
            e.printStackTrace();
        }
    }
    //////////////////////////////////////////////////////////////////////////

}

