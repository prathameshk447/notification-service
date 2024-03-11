package org.dnyanyog.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.dnyanyog.NotificationServiceMain;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = NotificationServiceMain.class)
public class NotificationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void verifyNotificationOperationForNotificationSuccess() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/notification/v1/notify")
                .content("{\r\n"
                        + "\"clientId\":\"CLIENT001\",\r\n"
                        + "\"mode\": \"EMAIL\",\r\n"
                        + "\"subject\": \"Important Notification\",\r\n"
                        + "\"body\": \"Hello, this is an important notification.\",\r\n"
                        + "\"footer\": \"Best regards, Your App\",\r\n"
                        + "\"from\" : \"prathameshkulkarni047@gmail.com\",\r\n"
                        + "\"to\": \"prathameshkulkarni47@gmail.com\"\r\n"
                        + "}")
                .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk()) 
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Success"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0000"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Notification sent successfully!"))
                .andReturn();
    }
    
    @Test
    public void verifyNotificationOperationForIncompleteDataProvided() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/notification/v1/notify")
                .content("{\r\n"
                        + "\"clientId\":\"CLIENT001\",\r\n"
                        + "\"mode\": \"EMAIL\",\r\n"
                        + "\"body\": \"Hello, this is an important notification.\",\r\n"
                        + "\"footer\": \"Best regards, Your App\",\r\n"
                        + "\"from\" : \"prathameshkulkarni047@gmail.com\",\r\n"
                        + "\"to\": \"prathameshkulkarni47@gmail.com\"\r\n"
                        + "}")
                .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk()) 
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("error"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("NOTI0001"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Incomplete data sent"))
                .andReturn();
    }
    
    @Test
    public void verifyNotificationOperationForInvalidMode() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/notification/v1/notify")
                .content("{\r\n"
                        + "\"clientId\":\"CLIENT001\",\r\n"
                        + "\"mode\": \"MAIL\",\r\n"
                        + "\"subject\": \"Important Notification\",\r\n"
                        + "\"body\": \"Hello, this is an important notification.\",\r\n"
                        + "\"footer\": \"Best regards, Your App\",\r\n"
                        + "\"from\" : \"prathameshkulkarni047@gmail.com\",\r\n"
                        + "\"to\": \"prathameshkulkarni47@gmail.com\"\r\n"
                        + "}")
                .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk()) 
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("error"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("NOTI0002"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Invalid notification mode"))
                .andReturn();
    }
    
    @Test
    public void verifyNotificationOperationForToEmailIfModeIsMail() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/notification/v1/notify")
                .content("{\r\n"
                        + "\"clientId\":\"CLIENT001\",\r\n"
                        + "\"mode\": \"EMAIL\",\r\n"
                        + "\"subject\": \"Important Notification\",\r\n"
                        + "\"body\": \"Hello, this is an important notification.\",\r\n"
                        + "\"footer\": \"Best regards, Your App\",\r\n"
                        + "\"from\" : \"prathameshkulkarni047@gmail.com\",\r\n"
                        + "\"to\": \"prathameshkulkarni47@gmail.com\"\r\n"
                        + "}")
                .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk()) 
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("error"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("NOTI0003"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Invalid email address for To EMAIL"))
                .andReturn();
    }
}
