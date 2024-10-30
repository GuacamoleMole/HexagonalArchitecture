package com.prueba.PruebaTecnica.application.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SortProductsRequestDTO {
    private float salesWeight;
    private float stockWeight;
    private List<ProductSalesDTO> productSales;
    private List<ProductStockDTO> productStock;

}
