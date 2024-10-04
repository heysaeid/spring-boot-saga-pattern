package com.saga_choreography.order.application.service;


import com.saga_choreography.order.application.dto.OrderDTO;
import com.saga_choreography.order.application.mapper.CreateDeliveryEventMapper;
import com.saga_choreography.order.application.mapper.CreatePaymentEventMapper;
import com.saga_choreography.order.application.mapper.OrderMapper;
import com.saga_choreography.order.domain.entity.Order;
import com.saga_choreography.order.domain.entity.OrderStatus;
import com.saga_choreography.order.domain.repository.OrderRepository;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderService {
    @Autowired
    private KafkaProducerService kafkaProducerService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CreatePaymentEventMapper createPaymentEventMapper;
    @Autowired
    private CreateDeliveryEventMapper createDeliveryEventMapper;

    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = orderMapper.orderDTOToOrder(orderDTO);
        Order savedOrder = orderRepository.save(order);

        kafkaProducerService.sendMessage(
                KafkaProducerTopics.CREATE_PAYMENT,
                createPaymentEventMapper.orderToCreatePaymentEventDTO(savedOrder)
        );

        return orderMapper.orderToOrderDTO(savedOrder);
    }

    public Order confirmedOrder(Long orderId) {
        Order order = getOrderOrThrow(orderId);
        order.setStatus(OrderStatus.CONFIRMED);
        orderRepository.save(order);

        kafkaProducerService.sendMessage(
                KafkaProducerTopics.CREATE_DELIVERY,
                createDeliveryEventMapper.orderToCreateDeliveryEventDTO(order)
        );

        return order;
    }

    public Order cancelOrder(Long orderId) {
        Order order = getOrderOrThrow(orderId);
        order.setStatus(OrderStatus.CANCELED);
        orderRepository.save(order);
        return order;
    }

    private Order getOrderOrThrow(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));
    }

}
