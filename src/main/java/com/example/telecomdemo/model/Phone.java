package com.example.telecomdemo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Phone")
@Table(name = "phone")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "customer_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "customer_phone_fk"
            )
    )
    private Customer customer;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "phone_ext")
    private String phoneExt;

    @Column(name = "activated", nullable = false)
    private Boolean activated;

    @Column(name = "created_at", updatable = false, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Phone(Long id, Customer customer, String phoneNumber, Boolean activated, LocalDateTime createdAt) {
        this.id = id;
        this.customer = customer;
        this.phoneNumber = phoneNumber;
        this.activated = activated;
        this.createdAt = createdAt;
    }
}
