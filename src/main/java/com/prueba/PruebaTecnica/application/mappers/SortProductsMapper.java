package com.prueba.PruebaTecnica.application.mappers;

import com.prueba.PruebaTecnica.application.dto.ProductStockDTO;
import com.prueba.PruebaTecnica.application.dto.SortProductsRequestDTO;
import com.prueba.PruebaTecnica.application.dto.ProductSalesDTO;

import com.prueba.PruebaTecnica.application.sort_products.SortProductsRequest;
import com.prueba.PruebaTecnica.domain.exceptions.InvalidWeightException;
import com.prueba.PruebaTecnica.domain.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortProductsMapper {

    public static SortProductsRequest toSortProductsRequest(SortProductsRequestDTO dto) throws InvalidWeightException {
        List<Product> products = mapProducts(dto.getProductSales(), dto.getProductStock());

        return new SortProductsRequest(dto.getSalesWeight(), dto.getStockWeight(), products);
    }

    private static List<Product> mapProducts(List<ProductSalesDTO> sales, List<ProductStockDTO> stock) {
        Map<String, Product> productsMap = new HashMap<>();

        for (ProductStockDTO stockDTO : stock) {
            productsMap.put(stockDTO.getProductId(),
                Product.builder()
                    .productId(stockDTO.getProductId())
                    .stock(stockDTO.getStock())
                    .sales(0f)
                    .build());
        }

        for (ProductSalesDTO salesDTO : sales) {
            String productId = salesDTO.getProductId();

            if (productsMap.containsKey(productId)) {
                productsMap.get(productId).setSales(salesDTO.getSales());
            } else {
                productsMap.put(productId,
                    Product.builder()
                    .productId(productId)
                    .sales(salesDTO.getSales())
                    .stock(0)
                    .build());
            }
        }
        return new ArrayList<>(productsMap.values());
    }

}
