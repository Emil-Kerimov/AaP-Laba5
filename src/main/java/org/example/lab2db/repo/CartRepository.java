package org.example.lab2db.repo;

import org.example.lab2db.models.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart,Long> {
}
