package com.ecommerce.store.service;

import com.ecommerce.store.entity.Product;
import com.ecommerce.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product addProduct(Product product) {
        return repository.save(product);
    }

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public Product getProductById(int id) {
        return repository
                .findById(id)
                .orElse(null);
    }

    public String deleteProductById(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return "Product Deleted";
        } else return "Product Not Found";
    }

    public Product updateProduct(int id, Product product) {
        Product existingProduct = repository
                .findById(id)
                .orElse(null);
        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setBrand(product.getBrand());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setSpecification(product.getSpecification());
            existingProduct.setUrl(product.getUrl());
            existingProduct.setPrice(product.getPrice());
            return repository.save(existingProduct);
        } else return null;

    }
}
