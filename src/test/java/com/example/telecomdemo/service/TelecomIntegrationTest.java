package com.example.telecomdemo.service;

import com.example.telecomdemo.dto.PhonePageDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TelecomIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should Find All Phone Numbers")
    public void shouldGetAllPhoneNumbers() throws Exception {

        ResultActions actions = mockMvc.perform(get("/api/v1/telecom/phone/all?page=0&size=5")
                .contentType(MediaType.APPLICATION_JSON));
        actions.andExpect(status().isOk());

        MvcResult mvcResult = actions.andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        PhonePageDto result = jsonToPhonePageDto(content);

        assertThat(result.getPhones().size()).isEqualTo(5);
        assertThat(result.getCurrentPage()).isEqualTo(0);
    }

    @Test
    @DisplayName("Should Get Customer All Phone Numbers")
    public void shouldGetCustomerAllPhoneNumbers() throws Exception {

        ResultActions actions = mockMvc.perform(get("/api/v1/telecom/phone/1")
                .contentType(MediaType.APPLICATION_JSON));
        actions.andExpect(status().isOk());

        MvcResult mvcResult = actions.andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        List<String> result = jsonToListString(content);

        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Should Activate a phone number")
    public void shouldActivatePhoneNumber() throws Exception {

        ResultActions actions = mockMvc.perform(put("/api/v1/telecom/phone/activate/3/0491970156")
                .contentType(MediaType.APPLICATION_JSON));
        actions.andExpect(status().isOk());
    }

    private String jsonToObject(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            fail("Failed to convert object to json");
            return null;
        }
    }

    private PhonePageDto jsonToPhonePageDto(String jsonString) {
        try {
            return new ObjectMapper().readValue(jsonString, PhonePageDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            fail("Failed to convert json to PhonePageDto");
            return null;
        }
    }

    private List<String> jsonToListString(String jsonString) {
        try {
            return new ObjectMapper().readValue(jsonString, new TypeReference<List<String>>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            fail("Failed to convert object to list string");
            return null;
        }
    }
}