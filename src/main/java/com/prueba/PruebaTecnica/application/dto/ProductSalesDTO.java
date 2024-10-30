package com.prueba.PruebaTecnica.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductSalesDTO {
    private String productId;
    private Float sales;
}
