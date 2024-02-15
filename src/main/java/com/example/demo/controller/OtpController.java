package com.example.demo.controller;

import com.example.demo.service.OtpService;
import com.example.demo.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

import static com.example.demo.util.OtpGenerator.generateOtp;
@RestController
public class OtpController {

    private final OtpService twilioSmsService;
    @Autowired
    private OtpService otpService;

    @Autowired
    public OtpController(OtpService twilioSmsService) {
        this.twilioSmsService = twilioSmsService;
    }


    //SENDING OTP

    //http://localhost:8080/send-otp?phoneNumber=%2B917007681042
    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestParam String phoneNumber){
        String otp = generateOtp();
        otpService.sendOtp(phoneNumber);
        return ResponseEntity.ok("OTP sent Succesfully" );
    }


    //VERIFICATION OF OTP

    //http://localhost:8080/verify-otp?phoneNumber=%2B917007681042&&otp=
    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestParam String phoneNumber, @RequestParam String otp){
        boolean isVerified =otpService.verifyOtp(phoneNumber, otp);
        if (isVerified){
            return ResponseEntity.ok("OTP verified Succesfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid OTP");
        }
    }





}
