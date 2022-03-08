package com.example.telecomdemo.service;

import com.example.telecomdemo.dto.PhonePageDto;
import com.example.telecomdemo.exception.DataNotExistException;
import com.example.telecomdemo.model.Customer;
import com.example.telecomdemo.model.Phone;
import com.example.telecomdemo.repository.CustomerRepository;
import com.example.telecomdemo.repository.PhoneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;

class PhoneServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private PhoneRepository phoneRepository;

    private PhoneService underTest;

    private Pageable paging;

    private Long customerId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.underTest = new PhoneService(customerRepository, phoneRepository);
        this.paging = PageRequest.of(0, 5);
        this.customerId = 1L;
    }

    @Test
    @DisplayName("Should Find All Phone Numbers")
    public void shouldFindAllPhoneNumbers() {
        // Given
        given(phoneRepository.findByActivated(true, this.paging)).willReturn(getPage());

        // When
        PhonePageDto result = underTest.findAllPhoneNumbers(this.paging);

        // Then
        assertThat(result.getPhones().size()).isEqualTo(5);
        assertThat(result.getTotalPages()).isEqualTo(2);
        assertThat(result.getTotalPhones()).isEqualTo(9);
        assertThat(result.getCurrentPage()).isEqualTo(0);

    }

    @Test
    @DisplayName("Should Find Customer's All Phone Numbers")
    public void shouldFindCustemerAllPhoneNumbers() {
        // Given
        Customer customer = new Customer(
                this.customerId,
                "name",
                "email",
                LocalDateTime.now(),
                Arrays.asList(
                        getActivatedPhone("123"),
                        getActivatedPhone("123"),
                        getActivatedPhone("123")
                ) // give 3 activated result
        );
        given(customerRepository.findById(this.customerId)).willReturn(Optional.of(customer));

        // When
        List<String> result = underTest.findCustomerAllPhoneNumbers(this.customerId);

        // Then
        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("Should Find Customer's All Phone Numbers Fail")
    public void shouldFindCustomerAllPhoneNumbersFail() {
        // Given
        given(customerRepository.findById(this.customerId)).willReturn(Optional.empty());

        // When
        // Then
        assertThatThrownBy(() -> underTest.findCustomerAllPhoneNumbers(customerId))
                .isInstanceOf(DataNotExistException.class)
                .hasMessageContaining("Customer is not existed:" + customerId);
    }

    @Test
    @DisplayName("Should Activate Phone Number")
    public void shouldActivatePhoneNumber() {
        // Given
        Customer customer = new Customer(
                this.customerId,
                "name",
                "email",
                LocalDateTime.now(),
                Arrays.asList(
                        getNotActivatedPhone("123"),
                        getNotActivatedPhone("234"),
                        getNotActivatedPhone("567")
                ) // give 3 not activated result
        );
        given(customerRepository.findById(this.customerId)).willReturn(Optional.of(customer));

        // When
        underTest.activatePhoneNumber(this.customerId, "123");

        // Then
        ArgumentCaptor<Set<Phone>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        then(phoneRepository).should().saveAll(argumentCaptor.capture());

        Set<Phone> argumentCaptorValue = argumentCaptor.getValue();
        assertThat(argumentCaptorValue.size()).isEqualTo(1);
    }

    private Phone getActivatedPhone(String phoneNumber) {
        Phone phone1 = new Phone(
                1L, new Customer(), phoneNumber, true, LocalDateTime.now()
        );
        return phone1;
    }

    private Phone getNotActivatedPhone(String phoneNumber) {
        Phone phone1 = new Phone(
                1L, new Customer(), phoneNumber, false, LocalDateTime.now()
        );
        return phone1;
    }

    private Page<Phone> getPage() {
        return new Page<Phone>() {

            @Override
            public Iterator<Phone> iterator() {
                return null;
            }

            @Override
            public int getTotalPages() {
                return 2;
            }

            @Override
            public long getTotalElements() {
                return 9;
            }

            @Override
            public int getNumber() {
                return 0;
            }

            @Override
            public int getSize() {
                return 0;
            }

            @Override
            public int getNumberOfElements() {
                return 0;
            }

            @Override
            public List<Phone> getContent() {
                return Arrays.asList(
                        new Phone(), new Phone(), new Phone(), new Phone(), new Phone()
                );
            }

            @Override
            public boolean hasContent() {
                return false;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public boolean isFirst() {
                return false;
            }

            @Override
            public boolean isLast() {
                return false;
            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Pageable nextPageable() {
                return null;
            }

            @Override
            public Pageable previousPageable() {
                return null;
            }

            @Override
            public <U> Page<U> map(Function<? super Phone, ? extends U> converter) {
                return null;
            }
        };

    }
}