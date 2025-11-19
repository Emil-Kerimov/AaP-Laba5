package org.example.lab2db.controllers;

import lombok.RequiredArgsConstructor;
import org.example.lab2db.repo.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping("/products")
    public String getProducts(Model model) {
        var products = productRepository.findAll();
        model.addAttribute("products", products);
        return "products";
    }
}