package com.mx.development.app.service;

import com.mx.development.app.entity.ProductEntity;
import org.springframework.stereotype.Service;


/**
 * Service class responsible for managing product-related operations.
 * Provides functionality to save, delete, update, and retrieve products.
 *
 * @author josesaidolanogarcia
 *
 */
@Service
public class ProductService {

    /**
     * Saves a new product with predefined attributes and returns the product entity.
     *
     * @return the ProductEntity object containing the details of the saved product
     */
    public ProductEntity saveProduct(){
        System.out.println("Guardando producto");
        ProductEntity product = new ProductEntity();
        product.setName("Producto");
        product.setDescription("Descripcion");
        product.setPrice("100");
        product.setCategory("Categoria");
        return product;
    }

    public void deleteProduct(){
        System.out.println("Eliminando producto");
    }

    public void updateProduct(){
        System.out.println("Actualizando producto");
    }

    /**
     * Retrieves a product based on the provided identifier.
     *
     * @param id the unique identifier of the product to retrieve
     * @return the ProductEntity object containing the product details
     */
    public ProductEntity getProduct(String id){
        System.out.println("Obteniendo producto con id: " + id);
        //Simulo ir  ala BD y obtengo el producto con el id - id
        ProductEntity product = new ProductEntity();
        product.setName("Producto1");
        product.setDescription("Descripcion1");
        product.setPrice("1");
        product.setCategory("Categoria1");
        return product;
    }

}
