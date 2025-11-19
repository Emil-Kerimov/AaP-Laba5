package org.example.lab2db.repo;

import org.example.lab2db.models.CartItem;
import org.springframework.data.repository.CrudRepository;

public interface CartItemRepository extends CrudRepository<CartItem,Long> {

    void deleteAllByCartId(Long id);
}
