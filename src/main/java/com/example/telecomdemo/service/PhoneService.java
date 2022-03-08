package com.example.telecomdemo.service;

import com.example.telecomdemo.dto.PhonePageDto;
import com.example.telecomdemo.exception.DataNotExistException;
import com.example.telecomdemo.model.Customer;
import com.example.telecomdemo.model.Phone;
import com.example.telecomdemo.repository.CustomerRepository;
import com.example.telecomdemo.repository.PhoneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PhoneService {
    private final CustomerRepository customerRepository;

    private final PhoneRepository phoneRepository;

    public PhoneService(CustomerRepository customerRepository, PhoneRepository phoneRepository) {
        this.customerRepository = customerRepository;
        this.phoneRepository = phoneRepository;
    }

    public PhonePageDto findAllPhoneNumbers(Pageable paging) {
        Page<Phone> all = phoneRepository.findByActivated(true, paging);
        List<Phone> phones = all.getContent();
        List<String> result = phones
                .stream()
                .map(v -> v.getPhoneNumber())
                .collect(Collectors.toList());

        PhonePageDto phonePageDto = new PhonePageDto();
        phonePageDto.setPhones(result);
        phonePageDto.setTotalPhones(all.getTotalElements());
        phonePageDto.setTotalPages(all.getTotalPages());
        phonePageDto.setCurrentPage(all.getNumber());
        return phonePageDto;
    }

    public List<String> findCustomerAllPhoneNumbers(Long customerId) {
        Customer customer = getCustomer(customerId);
        return customer.getPhones()
                .stream()
                .filter(v -> v.getActivated())
                .map(v -> v.getPhoneNumber())
                .collect(Collectors.toList());
    }

    public void activatePhoneNumber(Long customerId, String phoneNumber) {
        Customer customer = getCustomer(customerId);
        Set<Phone> phones = customer.getPhones()
                .stream()
                .filter(v -> phoneNumber.equals(v.getPhoneNumber()))
                .collect(Collectors.toSet());

        phones.forEach(v -> v.setActivated(true));

        phoneRepository.saveAll(phones);
    }

    private Customer getCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new DataNotExistException("Customer is not existed:" + customerId));
        return customer;
    }
}
