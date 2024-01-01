package io.thebootcode.medium.mediumapi.controllers;

import io.thebootcode.medium.mediumapi.models.products.Product;
import io.thebootcode.medium.mediumapi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/{uuid}")
    public ResponseEntity<Product> getProduct(
            @PathVariable String uuid
    ) {

        Product product = productService.getProductByUUID(uuid);

        if(product == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(product);
    }

}
