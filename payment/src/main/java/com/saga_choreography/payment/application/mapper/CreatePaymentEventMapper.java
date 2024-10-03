package com.saga_choreography.payment.application.mapper;

import com.saga_choreography.payment.application.dto.CreatePaymentEventDTO;
import com.saga_choreography.payment.domain.entity.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreatePaymentEventMapper {

    Payment createPaymentEventDTOToPayment(CreatePaymentEventDTO createPaymentEventDTO);

    CreatePaymentEventDTO paymentToCreatePaymentEventDTO(Payment payment);
}
