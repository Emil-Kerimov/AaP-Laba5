package org.example.lab2db;

import lombok.RequiredArgsConstructor;
import org.example.lab2db.models.Category;
import org.example.lab2db.repo.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        if (categoryRepository.count() == 0) {

            System.out.println("Створення початкових категорій");

            Category electronics = new Category("Електроніка");
            Category books = new Category("Книги");
            Category clothes = new Category("Одяг");

            categoryRepository.save(electronics);
            categoryRepository.save(books);
            categoryRepository.save(clothes);

            System.out.println("Початкові категорії успішно створено");
        } else {
            System.out.println("Категорії вже існують. Ініціалізація пропущена");
        }
    }
}