package com.jaeseok.catalogservice.controller;

import com.jaeseok.catalogservice.entity.CatalogEntity;
import com.jaeseok.catalogservice.mapper.CatalogMapper;
import com.jaeseok.catalogservice.service.CatalogService;
import com.jaeseok.catalogservice.vo.ResponseCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/catalog-service")
public class CatalogController {

    @Autowired
    Environment environment;

    @Autowired
    CatalogService catalogService;

    @GetMapping("/health_check")
    public String status() {
        String statusMessage = String.format("catalog service is working on port : %s",
                environment.getProperty("local.server.port"));
        return statusMessage;
    }

    @GetMapping("/catalogs")
    public ResponseEntity<List<ResponseCatalog>> getCatalogs() {
        Iterable<CatalogEntity> catalogList = catalogService.getAllCatalogs();

        List<ResponseCatalog> result = new ArrayList<>();
        catalogList.forEach(c -> result.add(CatalogMapper.INSTANCE.entityToResponse(c)));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }
}
