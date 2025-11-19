package org.example.lab2db.repo;

import org.example.lab2db.models.Image;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ImageRepository extends CrudRepository<Image, Long> {
    List<Image> findByProductId(Long id);
}
