package com.jaeseok.orderservice.mapper;

import com.jaeseok.orderservice.dto.OrderDto;
import com.jaeseok.orderservice.entity.OrderEntity;
import com.jaeseok.orderservice.vo.RequestOrder;
import com.jaeseok.orderservice.vo.ResponseOrder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderEntity dtoToEntity(OrderDto orderDto);
    OrderDto entityToDto(OrderEntity orderEntity);
    OrderDto requestToDto(RequestOrder requestOrder);
    ResponseOrder dtoToResponse(OrderDto orderDto);
    ResponseOrder entityToResponse(OrderEntity orderEntity);
}
