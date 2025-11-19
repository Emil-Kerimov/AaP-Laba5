package org.example.lab2db.controllers;

import lombok.RequiredArgsConstructor;
import org.example.lab2db.repo.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public String getCategories(Model model) {
        var categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "categories";
    }
}