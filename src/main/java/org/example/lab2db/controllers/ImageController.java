package org.example.lab2db.controllers;

import lombok.RequiredArgsConstructor;
import org.example.lab2db.repo.ImageRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class ImageController {

    private final ImageRepository imageRepository;

    @GetMapping("/images")
    public String getImages(Model model) {
        var images = imageRepository.findAll();
        model.addAttribute("images", images);
        return "images";
    }
}