package io.thebootcode.medium.mediumapi.services;

import io.thebootcode.medium.mediumapi.models.products.Product;
import io.thebootcode.medium.mediumapi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product getProductByUUID(String puuid) {
        Optional<Product> productOptional = productRepository.findProductByUuid(puuid);
        return productOptional.orElse(null);
    }
}
