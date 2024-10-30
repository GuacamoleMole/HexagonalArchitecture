package com.prueba.PruebaTecnica;

import com.prueba.PruebaTecnica.application.dto.ProductSalesDTO;
import com.prueba.PruebaTecnica.application.dto.ProductStockDTO;
import com.prueba.PruebaTecnica.application.dto.SortProductsRequestDTO;
import com.prueba.PruebaTecnica.application.sort_products.SortProductResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = PruebaTecnicaApplication.class)
public class SortProductsIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldWorksFirstCase(){

        SortProductsRequestDTO request = SortProductsRequestDTO.builder().build();
        request.setSalesWeight(0.5f);
        request.setStockWeight(0.5f);
        request.setProductSales(Arrays.asList(
                ProductSalesDTO.builder().productId("1").sales(50000f).build(),
                ProductSalesDTO.builder().productId("2").sales(100000f).build(),
                ProductSalesDTO.builder().productId("3").sales(100000f).build(),
                ProductSalesDTO.builder().productId("4").sales(75000f).build()
        ));
        request.setProductStock(Arrays.asList(
                ProductStockDTO.builder().productId("1").stock(100000).build(),
                ProductStockDTO.builder().productId("2").stock(400000).build(),
                ProductStockDTO.builder().productId("3").stock(200000).build(),
                ProductStockDTO.builder().productId("4").stock(300000).build()
        ));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        HttpEntity<SortProductsRequestDTO> entity = new HttpEntity<>(request, headers);
        ResponseEntity<SortProductResponse> res = restTemplate.postForEntity("/sort-products", entity, SortProductResponse.class);

        SortProductResponse response = res.getBody();

        assertEquals(response.getProductIds().get(0), "2");
        assertEquals(response.getProductIds().get(1), "4");
        assertEquals(response.getProductIds().get(2), "3");
        assertEquals(response.getProductIds().get(3), "1");
        assertTrue(res.getStatusCode().is2xxSuccessful());
    }
}
