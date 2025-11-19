package org.example.lab2db.repo;

import org.example.lab2db.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Category findByName(String name);

    boolean existsByName(String name);
}