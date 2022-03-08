package com.example.telecomdemo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class PhonePageDto {
    private List<String> phones;
    private long totalPhones;
    private int totalPages;
    private int currentPage;

    public PhonePageDto() {
    }

    public PhonePageDto(List<String> phones, long totalPhones, int totalPages, int currentPage) {
        this.phones = phones;
        this.totalPhones = totalPhones;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
    }
}
