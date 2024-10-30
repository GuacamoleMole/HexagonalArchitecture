package com.prueba.PruebaTecnica;
import com.prueba.PruebaTecnica.application.sort_products.SortProductResponse;
import com.prueba.PruebaTecnica.application.sort_products.SortProductsRequest;
import com.prueba.PruebaTecnica.application.sort_products.SortProductsUseCase;
import com.prueba.PruebaTecnica.domain.exceptions.InvalidWeightException;
import com.prueba.PruebaTecnica.domain.model.Product;
import com.prueba.PruebaTecnica.domain.services.ProductService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SortProductsBussinessTest {

    private final ProductService productService = new ProductService();
    private SortProductsUseCase sortProductUseCase = new SortProductsUseCase(productService);;

    @Test
    void shouldSortProductsWhenSalesAndStockWeightsAreEqual() throws InvalidWeightException {
        List<Product> products = new ArrayList<>();
        products.add(Product.builder().productId("1").sales(50000f).stock(100000).build());
        products.add(Product.builder().productId("2").sales(100000f).stock(400000).build());
        products.add(Product.builder().productId("3").sales(100000f).stock(200000).build());
        products.add(Product.builder().productId("4").sales(75000f).stock(300000).build());

        SortProductsRequest request = new SortProductsRequest(0.5f, 0.5f, products);

        SortProductResponse response = sortProductUseCase.execute(request);
        assertEquals(response.getProductIds().get(0), "2");
        assertEquals(response.getProductIds().get(1), "4");
        assertEquals(response.getProductIds().get(2), "3");
        assertEquals(response.getProductIds().get(3), "1");

    }

    @Test
    void shouldSortProductsWhenOnlyMattersSalesWeight() throws InvalidWeightException {
        List<Product> products = new ArrayList<>();
        products.add(Product.builder().productId("1").sales(110000f).stock(100000).build());
        products.add(Product.builder().productId("2").sales(100000f).stock(400000).build());
        products.add(Product.builder().productId("3").sales(1200000f).stock(200000).build());

        SortProductsRequest request = new SortProductsRequest(1f, 0f, products);

        SortProductResponse response = sortProductUseCase.execute(request);
        assertEquals(response.getProductIds().get(0), "3");
        assertEquals(response.getProductIds().get(1), "1");
        assertEquals(response.getProductIds().get(2), "2");

    }

    @Test
    void shouldSortProductsWhenOnlyMattersStockWeight() throws InvalidWeightException {
        List<Product> products = new ArrayList<>();
        products.add(Product.builder().productId("1").sales(50000f).stock(1050000).build());
        products.add(Product.builder().productId("2").sales(100000f).stock(400000).build());
        products.add(Product.builder().productId("3").sales(100000f).stock(200000).build());

        SortProductsRequest request = new SortProductsRequest(0f, 1f, products);

        SortProductResponse response = sortProductUseCase.execute(request);
        assertEquals(response.getProductIds().get(0), "1");
        assertEquals(response.getProductIds().get(1), "2");
        assertEquals(response.getProductIds().get(2), "3");
    }

    @Test
    void shouldSortProductsWhenSalesAndStockWeightsAreNotEqual() throws InvalidWeightException {
        List<Product> products = new ArrayList<>();
        products.add(Product.builder().productId("1").sales(50000f).stock(100000).build());
        products.add(Product.builder().productId("2").sales(100000f).stock(400000).build());
        products.add(Product.builder().productId("3").sales(100000f).stock(200000).build());
        products.add(Product.builder().productId("4").sales(75000f).stock(300000).build());

        SortProductsRequest request = new SortProductsRequest(0.25f, 0.75f, products);

        SortProductResponse response = sortProductUseCase.execute(request);
        assertEquals(response.getProductIds().get(0), "2");
        assertEquals(response.getProductIds().get(1), "4");
        assertEquals(response.getProductIds().get(2), "3");
        assertEquals(response.getProductIds().get(3), "1");

    }

    @Test
    void shouldThrowExceptionWhenSalesWeightsIsIncorrect() throws InvalidWeightException {
        List<Product> products = new ArrayList<>();
        products.add(Product.builder().productId("1").sales(50000f).stock(100000).build());
        assertThrows(InvalidWeightException.class, () -> {
            SortProductsRequest request = new SortProductsRequest(1.5f, 0.5f, products);
        });
        assertThrows(InvalidWeightException.class, () -> {
            SortProductsRequest request = new SortProductsRequest(-1.5f, 0.5f, products);
        });
    }

    @Test
    void shouldThrowExceptionWhenStockWeightsIsIncorrect() throws InvalidWeightException {
        List<Product> products = new ArrayList<>();
        products.add(Product.builder().productId("1").sales(50000f).stock(100000).build());
        assertThrows(InvalidWeightException.class, () -> {
            SortProductsRequest request = new SortProductsRequest(1f, 1.1f, products);
        });
        assertThrows(InvalidWeightException.class, () -> {
            SortProductsRequest request = new SortProductsRequest(1f, -0.1f, products);
        });
    }
}
