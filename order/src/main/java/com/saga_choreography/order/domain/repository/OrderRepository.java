package com.saga_choreography.order.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saga_choreography.order.domain.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
    List<Order> findByCustomerId(Integer customerId);
}
