package com.example.telecomdemo.controller;

import com.example.telecomdemo.dto.PhonePageDto;
import com.example.telecomdemo.service.PhoneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Telecom API")
@RestController
@RequestMapping("api/v1/telecom")
@RequiredArgsConstructor
public class TelecomDemoController {

    private final PhoneService service;

    @Operation(summary = "get all phone numbers")
    @GetMapping("/phone/all")
    public PhonePageDto getAllPhoneNumbers(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "5") int size) {
        return service.findAllPhoneNumbers(PageRequest.of(page, size));
    }

    @Operation(summary = "get all phone numbers of a single customer")
    @GetMapping("/phone/{customerId}")
    public List<String> getCustomerAllPhoneNumbers(@PathVariable Long customerId) {
        return service.findCustomerAllPhoneNumbers(customerId);
    }

    @Operation(summary = "activate a phone number")
    @PutMapping("/phone/activate/{customerId}/{phoneNumber}")
    public void activatePhoneNumber(@PathVariable Long customerId, @PathVariable String phoneNumber) {
        service.activatePhoneNumber(customerId, phoneNumber);
    }

}
