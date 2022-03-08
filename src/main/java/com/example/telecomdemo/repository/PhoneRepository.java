package com.example.telecomdemo.repository;

import com.example.telecomdemo.model.Phone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PhoneRepository extends PagingAndSortingRepository<Phone, Long> {
    Page<Phone> findByActivated(boolean activated, Pageable pageable);

}
