package com.prueba.PruebaTecnica.infrastructure.rest;

import com.prueba.PruebaTecnica.application.dto.SortProductsRequestDTO;
import com.prueba.PruebaTecnica.application.mappers.SortProductsMapper;
import com.prueba.PruebaTecnica.application.sort_products.SortProductResponse;
import com.prueba.PruebaTecnica.application.sort_products.SortProductsRequest;
import com.prueba.PruebaTecnica.application.sort_products.SortProductsUseCase;
import com.prueba.PruebaTecnica.domain.exceptions.InvalidWeightException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    private SortProductsUseCase useCase;

    @PostMapping("/sort-products")
    public ResponseEntity<SortProductResponse> sortProducts(@RequestBody SortProductsRequestDTO requestDTO) throws InvalidWeightException {
        SortProductsRequest request = SortProductsMapper.toSortProductsRequest(requestDTO);
        SortProductResponse response = useCase.execute(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
