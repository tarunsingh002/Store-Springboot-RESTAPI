package com.ecommerce.store.controller;

import com.ecommerce.store.entity.Product;
import com.ecommerce.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://store4.vercel.app", maxAge = 3600)
//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/api/v1/admin/addproduct")
    public Product addProduct(@RequestBody Product product) {
        return service.addProduct(product);
    }

    @PutMapping("/api/v1/admin/updateproduct/{id}")
    public Product updateProduct(@RequestBody Product product,
                                 @PathVariable int id) {
        return service.updateProduct(id, product);
    }

    @DeleteMapping("/api/v1/admin/deleteproduct/{id}")
    public String deleteProduct(@PathVariable int id) {
        return service.deleteProductById(id);
    }

    @GetMapping("/api/v1/all/getproducts")
    public List<Product> getProducts() {
        return service.getProducts();
    }

    @GetMapping("/api/v1/all/getproduct/{id}")
    public Product getProduct(@PathVariable int id) {
        return service.getProductById(id);
    }
}
