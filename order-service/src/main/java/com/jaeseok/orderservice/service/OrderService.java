package com.jaeseok.orderservice.service;

import com.jaeseok.orderservice.dto.OrderDto;
import com.jaeseok.orderservice.entity.OrderEntity;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDetails);
    OrderDto getOrderByOrderId(String orderId);
    Iterable<OrderEntity> getOrderByUserId(String userId);
}
