package com.jaeseok.catalogservice.service;

import com.jaeseok.catalogservice.entity.CatalogEntity;

public interface CatalogService {
    Iterable<CatalogEntity> getAllCatalogs();
}
