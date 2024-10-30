package com.prueba.PruebaTecnica.application.sort_products;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class SortProductResponse {
    private List<String> productIds;

    public SortProductResponse() {
        this.productIds = new ArrayList<>();
    }

    public List<String> getProductIds(){
        return this.productIds;
    }
}
