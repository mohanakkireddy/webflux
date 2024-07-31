package com.reactive.webflux.controller;

import com.reactive.webflux.dto.ProductDto;
import com.reactive.webflux.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/products")
public class FluxAndMonoController {
    @Autowired
    private ProductService productService;
    @GetMapping("/flux")
    public Flux<Integer> returnFlux(){
        return Flux.just(1,2,3,4)
                .log();
    };
    @GetMapping(value = "/fluxstream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Integer> returnFluxStream(){
        return Flux.just(1,2,3,4)
                .delayElements(Duration.ofSeconds(1))
                .log();
    };
    @GetMapping(value = "/mono")
    public Mono<Integer> returnMono(){
        return Mono.just(1)
                .log();
    };
    @PostMapping("/add")
    public Mono<ProductDto> addProduct(@RequestBody ProductDto productDto){
        Mono<ProductDto> productDtoMono = Mono.just(productDto);
        productDtoMono.subscribe(dto -> {
            System.out.println("Received ProductDto: " + dto);
        });
        return productService.addProduct(productDtoMono);
    }
    @GetMapping("/get")
    public Flux<ProductDto> getAllProduct(){
        return productService.getAllProducts();
    }



}
