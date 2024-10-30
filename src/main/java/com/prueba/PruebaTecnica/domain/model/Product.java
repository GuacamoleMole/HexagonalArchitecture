package com.prueba.PruebaTecnica.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private String productId;
    private Float sales;
    private Integer stock;
}
