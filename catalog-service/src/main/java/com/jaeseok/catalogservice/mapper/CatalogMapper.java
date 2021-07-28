package com.jaeseok.catalogservice.mapper;

import com.jaeseok.catalogservice.entity.CatalogEntity;
import com.jaeseok.catalogservice.vo.ResponseCatalog;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CatalogMapper {

    CatalogMapper INSTANCE = Mappers.getMapper(CatalogMapper.class);

    ResponseCatalog entityToResponse(CatalogEntity catalogEntity);
}
