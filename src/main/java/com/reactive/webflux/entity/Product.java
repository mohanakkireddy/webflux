package com.reactive.webflux.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "products")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "products_index")
public class Product {
    @Id
    private String id;
    private String name;
    private int qty;
    private double price;
}
