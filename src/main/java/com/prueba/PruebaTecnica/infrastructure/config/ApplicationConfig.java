package com.prueba.PruebaTecnica.infrastructure.config;

import com.prueba.PruebaTecnica.application.sort_products.SortProductsUseCase;
import com.prueba.PruebaTecnica.domain.services.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public SortProductsUseCase sortProductsUseCase(ProductService productService){
        return new SortProductsUseCase(productService);
    }

    @Bean
    public ProductService productService(){
        return new ProductService();
    }
}
