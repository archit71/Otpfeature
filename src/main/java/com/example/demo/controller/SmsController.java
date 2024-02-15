package com.example.demo.controller;

import com.example.demo.payload.SmsRequest;
import com.example.demo.service.SmsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.SecureRandom;

import static com.example.demo.util.OtpGenerator.generateOtp;

@RestController

public class SmsController {

    private final SmsService twilioSmsService;

    @Autowired
    private SmsService smsService;

    @Autowired
    public SmsController(SmsService twilioSmsService) {
        this.twilioSmsService = twilioSmsService;
    }

    //http://localhost:8080/send-sms
    @PostMapping("/send-sms")
    public void sendSms(@RequestBody SmsRequest smsRequest) {
        twilioSmsService.sendSms(smsRequest.getTo(), smsRequest.getMessage());
    }



}
