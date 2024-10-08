package com.saga_choreography.payment.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity @Table(name = "payments")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate = LocalDateTime.now();

    private Float amount;
    private PaymentStatus status = PaymentStatus.PENDING;

}
