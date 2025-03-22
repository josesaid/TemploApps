package com.mx.development.app.entity;

import lombok.Data;

@Data
public class ProductEntity {
    private String name;
    private String description;
    private String price;
    private String category;

    private ProductDetails productDetails;

    public ProductEntity(){
        productDetails = new ProductDetails();
        productDetails.setNameDealer("Dealer");
        productDetails.setNameProduct("Product");
        productDetails.setPriceDealer("Price");
    }

}
