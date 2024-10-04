package com.saga_choreography.payment.application.mapper;

import com.saga_choreography.payment.application.dto.CreatePaymentDTO;
import com.saga_choreography.payment.domain.entity.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreatePaymentMapper {

    Payment createPaymentDTOToPayment(CreatePaymentDTO createPaymentDTO);

    CreatePaymentDTO paymentToCreatePaymentDTO(Payment payment);
}
