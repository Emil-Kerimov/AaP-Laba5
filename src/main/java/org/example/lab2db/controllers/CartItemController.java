package org.example.lab2db.controllers;

import lombok.RequiredArgsConstructor;
import org.example.lab2db.repo.CartItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class CartItemController {

    private final CartItemRepository cartItemRepository;

    @GetMapping("/cart-items")
    public String getCartItems(Model model) {
        var cartItems = cartItemRepository.findAll();
        model.addAttribute("cartItems", cartItems);
        return "cart-items";
    }
}