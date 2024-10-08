package com.saga_choreography.payment.domain.repository;

import com.saga_choreography.payment.domain.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
