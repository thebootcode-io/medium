package io.thebootcode.medium.mediumapi.repositories;

import io.thebootcode.medium.mediumapi.models.account.User;
import io.thebootcode.medium.mediumapi.models.products.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{

    Optional<Product> findProductByUuid(String uuid);

}