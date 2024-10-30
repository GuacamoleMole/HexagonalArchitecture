package com.prueba.PruebaTecnica.application.sort_products;

import com.prueba.PruebaTecnica.domain.model.Product;
import com.prueba.PruebaTecnica.domain.services.ProductService;

import java.util.List;

public class SortProductsUseCase {

    private ProductService productService;

    public SortProductsUseCase(ProductService productService){
        this.productService = productService;
    }

    public SortProductResponse execute(SortProductsRequest request) {
        List<Product> products = productService.sortProducts(request.getSalesWeight(), request.getStockWeight(), request.getProducts());
        SortProductResponse response = new SortProductResponse();
        for(Product product : products){
            response.getProductIds().add(product.getProductId());
        }
        return response;
    }
}
