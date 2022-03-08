package com.example.telecomdemo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity(name = "Customer")
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "created_at", updatable = false, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "customer"
    )
    private List<Phone> phones = new ArrayList<>();

    public Customer(Long id, String name, String email, LocalDateTime createdAt, List<Phone> phones) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.phones = phones;
    }
}
