package com.test.smsproject.controller;

import com.test.smsproject.dto.requests.SmsRequest;
import com.test.smsproject.dto.responses.SmsResponse;
import com.test.smsproject.service.SmsRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/sms")
public class SmsRegistrationController {

    @Autowired
    private SmsRegistrationService smsRegistrationService;

    @PostMapping
    public ResponseEntity<SmsResponse> createSms(@RequestBody SmsRequest request) {
        SmsResponse smsMessages = smsRegistrationService.sendSms(request);

        return ResponseEntity.ok(smsMessages);
    }
}

