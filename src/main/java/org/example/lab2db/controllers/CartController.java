package org.example.lab2db.controllers;

import lombok.RequiredArgsConstructor;
import org.example.lab2db.repo.CartRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class CartController {
    private final CartRepository cartRepository;

    @GetMapping("/shop")
    public String getCart(Model model){
        var carts = cartRepository.findAll();
        model.addAttribute("carts", carts);
        return "cart";
    }

}
