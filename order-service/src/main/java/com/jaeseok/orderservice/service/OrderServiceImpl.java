package com.jaeseok.orderservice.service;

import com.jaeseok.orderservice.dto.OrderDto;
import com.jaeseok.orderservice.entity.OrderEntity;
import com.jaeseok.orderservice.mapper.OrderMapper;
import com.jaeseok.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository;

    @Override
    public OrderDto createOrder(OrderDto orderDetails) {
        String createdOrderId = UUID.randomUUID().toString();
        orderDetails.setOrderId(createdOrderId);

        OrderEntity orderEntity = OrderMapper.INSTANCE.dtoToEntity(orderDetails);
        orderEntity = orderRepository.save(orderEntity);

        OrderDto returnOrderDto = OrderMapper.INSTANCE.entityToDto(orderEntity);
        return returnOrderDto;
    }

    @Override
    public OrderDto getOrderByOrderId(String orderId) {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId)
                .orElseThrow(() -> {
                    throw new NoSuchElementException();
                });

        OrderDto returnOrderDto = OrderMapper.INSTANCE.entityToDto(orderEntity);
        return returnOrderDto;
    }

    @Override
    public Iterable<OrderEntity> getOrderByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }
}
