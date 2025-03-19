package com.mx.development.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProductsController {

    @Autowired
    private ProductService productService;


    @GetMapping("/saludo")
    public String saludo(){
        System.out.println("Hola desde el controlador1");
        return "Hola desde el controlador2";
    }

    @GetMapping("/save")
    public ProductEntity save(){
        return productService.saveProduct();
    }

    @GetMapping("/get/{id}")
    public ProductEntity getProduct(@PathVariable String id){
        return productService.getProduct(id);
    }

    @GetMapping("/marco")
    public String saludarMarco(){
        return "Hola Marco de nuevo";
    }
}
