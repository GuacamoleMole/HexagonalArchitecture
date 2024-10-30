package com.prueba.PruebaTecnica.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductStockDTO {
    private String productId;
    private Integer stock;
}
