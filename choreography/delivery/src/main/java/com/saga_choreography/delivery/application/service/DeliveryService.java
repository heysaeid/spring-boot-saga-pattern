package com.saga_choreography.delivery.application.service;

import com.saga_choreography.delivery.application.dto.AssignDeliveryToPersonEventDTO;
import com.saga_choreography.delivery.application.dto.CancelPaymentEventDTO;
import com.saga_choreography.delivery.application.dto.CreateDeliveryDTO;
import com.saga_choreography.delivery.application.mapper.CreateDeliveryMapper;
import com.saga_choreography.delivery.domain.entity.Delivery;
import com.saga_choreography.delivery.domain.entity.DeliveryPerson;
import com.saga_choreography.delivery.domain.repository.DeliveryRepository;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private DeliveryPersonService deliveryPersonService;
    @Autowired
    private KafkaProducerService kafkaProducerService;
    @Autowired
    private CreateDeliveryMapper createDeliveryMapper;

    public Delivery createDelivery(CreateDeliveryDTO createDeliveryDTO) {
        Delivery delivery = createDeliveryMapper.createDeliveryDTOToDelivery(createDeliveryDTO);
        Delivery savedDelivery = deliveryRepository.save(delivery);
        kafkaProducerService.sendMessage(
                KafkaProducerTopics.ASSIGN_DELIVERY_TO_PERSON,
                new AssignDeliveryToPersonEventDTO(delivery.getId())
        );
        return savedDelivery;
    }

    public Boolean assignDeliveryToPerson(Long deliverId) {
        Delivery delivery = deliveryRepository.findById(deliverId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + deliverId)
        );
        DeliveryPerson deliveryPerson = deliveryPersonService.getAssignableDeliveryPerson(
                delivery.getProvince(),
                delivery.getCity()
        );
        if(deliveryPerson!=null){
            delivery.setDeliveryPerson(deliveryPerson);
            deliveryRepository.save(delivery);
            return true;
        } else {
            kafkaProducerService.sendMessage(
                    KafkaProducerTopics.CANCEL_PAYMENT,
                    new CancelPaymentEventDTO(delivery.getPaymentId())
            );
            return false;
        }
    }
}
