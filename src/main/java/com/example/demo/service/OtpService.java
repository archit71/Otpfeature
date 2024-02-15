package com.example.demo.service;

import com.example.demo.Configuration.TwilioConfiguration;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.example.demo.util.OtpGenerator.generateOtp;

@Service
public class OtpService {
    @Value("${twilio.accountSid}")
    private String accountSid;

    @Value("${twilio.authToken}")
    private String authToken;

    @Value("${twilio.phoneNumber}")
    private String twilioPhoneNumber;

    public void sendOtp(String phoneNumber) {
       String otp = generateOtp();
       otpmap.put(phoneNumber, otp);
       Twilio.init(twilioConfiguration.getAccountSid(), twilioConfiguration.getAuthToken());


        // Send SMS using Twilio
        Message message = Message.creator(
                        new PhoneNumber(phoneNumber),
                        new PhoneNumber(twilioConfiguration.getPhoneNumber()),
                        "Your OTP is: " + otp)
                .create();

        // Handle success or error if needed
        System.out.println("SMS sent with SID: " + message.getSid());
    }

    //VERIDICATION OF OTP
    @Autowired
    private TwilioConfiguration twilioConfiguration;

    private Map<String, String> otpmap = new HashMap<>();

    public boolean verifyOtp(String phoneNumber , String otp){
        if(otpmap.containsKey(phoneNumber)){
            String storedOtp = otpmap.get(phoneNumber);
            return storedOtp.equals(otp);
        }
        return false ;

    }

}
