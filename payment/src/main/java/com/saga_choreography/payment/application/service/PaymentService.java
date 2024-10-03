package com.saga_choreography.payment.application.service;

import com.saga_choreography.payment.application.dto.CancelOrderEventDTO;
import com.saga_choreography.payment.application.dto.ConfirmedOrderEventDTO;
import com.saga_choreography.payment.application.dto.CreatePaymentEventDTO;
import com.saga_choreography.payment.application.mapper.CreatePaymentEventMapper;
import com.saga_choreography.payment.domain.entity.Payment;
import com.saga_choreography.payment.domain.entity.PaymentStatus;
import com.saga_choreography.payment.domain.repository.PaymentRepository;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private KafkaProducerService kafkaProducerService;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private CreatePaymentEventMapper
            createPaymentEventMapper;

    public CreatePaymentEventDTO createPayment(CreatePaymentEventDTO createPaymentDTO) {
        Payment payment = createPaymentEventMapper.createPaymentEventDTOToPayment(createPaymentDTO);
        Payment savedPayment = paymentRepository.save(payment);

        return createPaymentEventMapper.paymentToCreatePaymentEventDTO(savedPayment);
    }

    public Payment confirmedPayment(Long paymentId) {
        Payment payment = this.getPaymentOrThrow(paymentId);
        payment.setStatus(PaymentStatus.COMPLETED);
        paymentRepository.save(payment);

        kafkaProducerService.sendMessage(
                KafkaProducerTopics.CONFIRMED_ORDER,
                new ConfirmedOrderEventDTO(payment.getOrderId())
        );

        return payment;
    }

    public Payment cancelPayment(Long paymentId) {
        Payment payment = this.getPaymentOrThrow(paymentId);
        payment.setStatus(PaymentStatus.REFUNDED);
        paymentRepository.save(payment);

        kafkaProducerService.sendMessage(
                KafkaProducerTopics.CANCEL_ORDER,
                new CancelOrderEventDTO(payment.getOrderId())
        );

        return payment;
    }

    private Payment getPaymentOrThrow(Long paymentId) {
        return paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + paymentId));
    }

}
