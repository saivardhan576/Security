package com.springsecurity.security.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.springsecurity.security.Entity.UserInfo;
import com.springsecurity.security.Service.ProductService; 
import com.springsecurity.security.dto.Product;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired 
    private ProductService service;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/new")
    public String addNewUser(@RequestBody UserInfo userInfo){
        return service.addUser(userInfo);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    // public List<UserInfo> getAllTheProducts() {
    //     return service.getProducts();
    // }
    public List<Product> getAllTheProducts() {
        return service.getProducts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    // public UserInfo getProductById(@PathVariable int id) {
    //     return service.getProduct(id);
    // }
    public Product getProductById(@PathVariable int id) {
        return service.getProduct(id);
    }
}
