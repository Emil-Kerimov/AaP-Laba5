package org.example.lab2db.controllers;

import lombok.RequiredArgsConstructor;
import org.example.lab2db.models.Category;
import org.example.lab2db.models.Product;
import org.example.lab2db.repo.CategoryRepository;
import org.example.lab2db.repo.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ProductController {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping("/products")
    public String getProducts(Model model) {
        var products = productRepository.findAll();
        model.addAttribute("products", products);
        return "products";
    }
    @GetMapping("/products/add")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryRepository.findAll());
        return "product-add";
    }

    @PostMapping("/products/add")
    public String addProduct(Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            return "product-add";
        }

        // Знайти категорію за ID або іншим полем, якщо CategoryController не дозволяє створювати нові
        Category category = product.getCategory();
        if (category != null && category.getId() != null) {
            Category existingCategory = categoryRepository.findById(category.getId()).orElse(null);
            product.setCategory(existingCategory);
        }

        productRepository.save(product);
        return "redirect:/products";
    }
}