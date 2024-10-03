package com.saga_choreography.payment.presentation.controller;


import com.saga_choreography.payment.application.service.PaymentService;
import com.saga_choreography.payment.domain.entity.Payment;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/{paymentId}/confirm")
    public ResponseEntity<Payment> confirmPayment(@PathVariable Long paymentId) {
        return ResponseEntity.ok(paymentService.confirmedPayment(paymentId));
    }

    @PostMapping("/{paymentId}/cancel")
    public ResponseEntity<Payment> cancelPayment(@PathVariable Long paymentId) {
        return ResponseEntity.ok(paymentService.cancelPayment(paymentId));
    }

}
