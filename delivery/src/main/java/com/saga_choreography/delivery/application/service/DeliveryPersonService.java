package com.saga_choreography.delivery.application.service;

import com.saga_choreography.delivery.domain.entity.DeliveryPerson;
import com.saga_choreography.delivery.domain.repository.DeliveryPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryPersonService {

    @Autowired
    private DeliveryPersonRepository deliveryPersonRepository;

    public DeliveryPerson getAssignableDeliveryPerson(String province, String city) {
        return deliveryPersonRepository.findByProvinceAndCity(province, city);
    }
}
