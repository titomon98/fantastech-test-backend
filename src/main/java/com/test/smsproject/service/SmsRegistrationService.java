package com.test.smsproject.service;

import com.test.smsproject.dto.requests.SmsRequest;
import com.test.smsproject.dto.responses.SmsResponse;
import com.test.smsproject.exceptions.SmsException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SmsRegistrationService {

    private static final int MAX = 160;
    private static final int SUFFIX = " ... - Part X of Y".length();
    public SmsResponse sendSms(SmsRequest request) {
        List<String> result = new ArrayList<>();

        //Validate different cases

        if (request.getNumber() == null || request.getNumber().isBlank()) {
            throw new SmsException("Phone number is required.", 400);
        }

        if(request.getNumber().length() < 7) {
            throw new SmsException("Phone number cannot be less than 7 numbers.", 400);
        }

        if (request.getMessage() == null || request.getMessage().isBlank()) {
            throw new SmsException("Message cannot be empty.", 400);
        }

        if (request.getMessage().length() > 10000) {
            throw new SmsException("Message is too long. Maximum allowed is 10,000 characters.", 413);
        }

        if (request.getNumber().equals("12345678")) {
            throw new SmsException("Some humor. That number doesn't exists", 418);
        }

        // Estimate in how many parts is going to be split
        int partLength = MAX - SUFFIX;

        List<String> chunks = splitMessage(request.getMessage(), partLength);

        int parts = chunks.size();
        for (int i = 0; i < parts; i++) {
            String part = chunks.get(i) + " ... - Part " + (i + 1) + " of " + parts;
            System.out.println("Sending SMS to " + request.getNumber() + ": " + part);
            result.add(part);
        }

        SmsResponse response = new SmsResponse();
        response.setSms(result);
        response.setCode(200);
        response.setMessage("SMS sent successfully.");

        return response;
    }

    private List<String> splitMessage(String message, int partLength) {
        List<String> parts = new ArrayList<>();

        int start = 0;
        while (start < message.length()) {
            // Calculate the end index for the current part
            int end = Math.min(start + partLength, message.length());

            // Extract the substring from start to end and add it to the list of parts
            parts.add(message.substring(start, end));

            // Move the start index forward to continue with the next part
            start = end;
        }

        return parts;
    }

}
