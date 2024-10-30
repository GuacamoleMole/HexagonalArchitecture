package com.prueba.PruebaTecnica.application.sort_products;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class SortProductResponse {
    @Builder.Default
    private List<String> productIds = new ArrayList<>();
}
