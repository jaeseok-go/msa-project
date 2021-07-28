package com.jaeseok.orderservice.controller;

import com.jaeseok.orderservice.dto.OrderDto;
import com.jaeseok.orderservice.entity.OrderEntity;
import com.jaeseok.orderservice.mapper.OrderMapper;
import com.jaeseok.orderservice.service.OrderService;
import com.jaeseok.orderservice.vo.RequestOrder;
import com.jaeseok.orderservice.vo.ResponseOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/order-service")
public class OrderController {

    @Autowired
    Environment environment;

    @Autowired
    OrderService orderService;

    @GetMapping("/health_check")
    public String status() {
        String statusMessage = String.format("order service is working on port : %s",
                environment.getProperty("local.server.port"));
        return statusMessage;
    }

    @PostMapping("/{userId}/orders")
    public ResponseEntity<ResponseOrder> createOrder(@PathVariable(name = "userId") String userId,
                                                     @RequestBody RequestOrder requestOrder) {
        OrderDto orderDto = OrderMapper.INSTANCE.requestToDto(requestOrder);
        orderDto.setUserId(userId);
        orderDto = orderService.createOrder(orderDto);

        ResponseOrder responseOrder = OrderMapper.INSTANCE.dtoToResponse(orderDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseOrder);
    }


    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> getOrder(@PathVariable(name = "userId") String userId) {
        Iterable<OrderEntity> orderList = orderService.getOrderByUserId(userId);

        List<ResponseOrder> result = new ArrayList<>();
        orderList.forEach(o -> result.add(OrderMapper.INSTANCE.entityToResponse(o)));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }
}
