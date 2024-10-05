package com.saga_choreography.delivery.domain.repository;

import com.saga_choreography.delivery.domain.entity.DeliveryPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryPersonRepository extends JpaRepository<DeliveryPerson, Long> {
    DeliveryPerson findByProvinceAndCity(String province, String city);
}
