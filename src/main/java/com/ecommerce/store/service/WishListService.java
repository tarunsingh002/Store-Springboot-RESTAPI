package com.ecommerce.store.service;

import com.ecommerce.store.entity.Product;
import com.ecommerce.store.entity.User;
import com.ecommerce.store.entity.WishList;
import com.ecommerce.store.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WishListService {
    @Autowired
    private WishListRepository repository;

    @Autowired
    private ProductService productService;

    public String addProduct(User user, Product product) {
        WishList wishList = repository.findByUser(user);
        List<Product> products = wishList.getProducts();
        products.add(product);
        wishList.setProducts(products);
        repository.save(wishList);
        return "Product added to Wishlist";
    }

    public List<Product> getWishList(User user) {
        return repository
                .findByUser(user)
                .getProducts();
    }


    public String removeProduct(int id, User user) {
        WishList wishList = repository.findByUser(user);
        List<Product> products = wishList.getProducts();
        products.remove(productService.getProductById(id));
        wishList.setProducts(products);
        repository.save(wishList);
        return "Product removed from wishlist";
    }

    public List<WishList> getAllWishLists() {
        return repository.findAll();
    }

    public String isProductWishListed(int id, User user) {
        WishList wishList = repository.findByUser(user);
        List<Product> products = wishList.getProducts();

        boolean isWishlisted = false;

        Set<Product> wp = products.stream().filter(p -> p.getProductId() == id).collect(Collectors.toSet());
        if (wp.size() > 0)
            return "Product is wishlisted";
        else
            return "Product is not wishlisted";
    }
}
