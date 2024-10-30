package com.prueba.PruebaTecnica.domain.services;

import com.prueba.PruebaTecnica.domain.model.Product;
import com.prueba.PruebaTecnica.domain.util.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class ProductService {
    public List<Product> sortProducts (float salesWeight, float stockWeight, List<Product> products){
        PriorityQueue<Pair<Product, Integer>> maxPriorityQueue = new PriorityQueue<>(
            new Comparator<Pair<Product, Integer>>() {
                @Override
                public int compare(Pair<Product, Integer> p1, Pair<Product, Integer> p2) {
                    return p2.getValue().compareTo(p1.getValue());
                }
            }
        );

        for (Product product : products) {
            Integer combinedValue = Math.round((salesWeight * product.getSales()) + (stockWeight * product.getStock()));
            maxPriorityQueue.add(new Pair<>(product, combinedValue));
        }

        for(Pair<Product, Integer> pair: maxPriorityQueue){
            System.out.println(pair.getValue());
        }

        List<Product> sortedProduct = new ArrayList<>();
        while (!maxPriorityQueue.isEmpty()) {
            sortedProduct.add(maxPriorityQueue.poll().getKey());
        }

        return sortedProduct;
    }
}
