package com.example.telecomdemo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TelecomErrorDto {
    private  String message;
    private  String details;
}
