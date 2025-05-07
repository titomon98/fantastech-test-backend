package com.fantastech.sms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.smsproject.controller.SmsRegistrationController;
import com.test.smsproject.dto.requests.SmsRequest;
import com.test.smsproject.dto.responses.SmsResponse;
import com.test.smsproject.service.SmsRegistrationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SmsRegistrationController.class)
public class SmsRegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private SmsRegistrationService smsRegistrationService;

    @Autowired
    private ObjectMapper objectMapper;

    public void testCreateSms() throws Exception {
        SmsRequest request = new SmsRequest();
        request.setNumber("+1234567890");
        request.setMessage("This is a test message that needs to be split into different messages");

        List<String> messages = List.of(
                "This is a test message... - Part 1 of 3",
                "that needs to be split... - Part 2 of 3",
                "into different messages... - Part 3 of 3"
        );

        SmsResponse mockResponse = new SmsResponse();
        mockResponse.setSms(messages);
        mockResponse.setCode(200);
        mockResponse.setMessage("SMS sent successfully.");

        Mockito.when(smsRegistrationService.sendSms(any(SmsRequest.class)))
                .thenReturn(mockResponse);

        mockMvc.perform(post("/api/sms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sms[0]").value(messages.get(0)))
                .andExpect(jsonPath("$.sms[1]").value(messages.get(1)))
                .andExpect(jsonPath("$.sms[2]").value(messages.get(2)))
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("SMS sent successfully."));
    }
}
