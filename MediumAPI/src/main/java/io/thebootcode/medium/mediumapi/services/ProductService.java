package io.thebootcode.medium.mediumapi.services;

import io.thebootcode.medium.mediumapi.models.products.Product;

public interface ProductService {

    Product getProductByUUID(String puuid);

}
