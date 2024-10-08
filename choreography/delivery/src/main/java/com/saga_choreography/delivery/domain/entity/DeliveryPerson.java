package com.saga_choreography.delivery.domain.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "delivery_persons")
public class DeliveryPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String province;

    private String city;

    @OneToMany(mappedBy = "deliveryPerson", cascade = CascadeType.ALL)
    private List<Delivery> deliveries = new ArrayList<>();

}
