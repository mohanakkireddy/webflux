package com.reactive.webflux.service;

import com.reactive.webflux.dto.ProductDto;
import com.reactive.webflux.entity.Product;

import com.reactive.webflux.repo.ProductRepo;
import com.reactive.webflux.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public Mono<ProductDto> addProduct(@RequestBody Mono<ProductDto> productDtoMono){
        return  productDtoMono.map(AppUtils::dtoToEntity)
                .flatMap(productRepo::insert)
                .map(AppUtils::entityToDto);
    }

    public Flux<ProductDto> getAllProducts() {
        return productRepo.findAll()
                .map(AppUtils::entityToDto);
    }

}
