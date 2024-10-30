package com.prueba.PruebaTecnica.application.sort_products;

import com.prueba.PruebaTecnica.domain.exceptions.InvalidWeightException;
import com.prueba.PruebaTecnica.domain.model.Product;
import lombok.Data;

import java.util.List;

@Data
public class SortProductsRequest {
    private float salesWeight;
    private float stockWeight;
    private List<Product> products;
    public SortProductsRequest(float salesWeight, float stockWeight, List<Product> products) throws InvalidWeightException {
        if (salesWeight < 0 || salesWeight > 1)
            throw new InvalidWeightException("salesWeight must be between 0 and 1");
        if (stockWeight < 0 || stockWeight > 1)
            throw new InvalidWeightException("stockWeight must be between 0 and 1");

        this.salesWeight = salesWeight;
        this.stockWeight = stockWeight;
        this.products = products;
    }
}
