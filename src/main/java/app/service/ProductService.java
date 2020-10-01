package app.service;

import app.model.Product;

import java.util.Optional;

public interface ProductService {
    Iterable<Product> findAll ();
    Optional<Product> findById(Long id);
    void save(Product product);
    void delete(Long id);

}
