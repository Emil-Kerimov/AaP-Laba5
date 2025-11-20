package org.example.lab2db;

import org.example.lab2db.models.Cart;
import org.example.lab2db.models.CartItem;
import org.example.lab2db.models.Category;
import org.example.lab2db.models.Product;
import org.example.lab2db.repo.CartItemRepository;
import org.example.lab2db.repo.CartRepository;
import org.example.lab2db.repo.CategoryRepository;
import org.example.lab2db.repo.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class CartRepositoryIntegrationTest extends BaseContainerConfig {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Test
    void cart_should_calculate_total_amount() {
        // Arrange
        Category cat = categoryRepository.save(new Category("Books"));
        Product product1 = productRepository.save(new Product("Book A", "Pub", new BigDecimal("300.00"), 10, "F", cat));
        Product product2 = productRepository.save(new Product("Book B", "Pub", new BigDecimal("100.00"), 5, "S", cat));

        Cart cart = new Cart();

        CartItem item1 = new CartItem(null, 2, product1.getPrice(), null, product1, null);
        item1.setTotalPrice(); // 600.00

        CartItem item2 = new CartItem(null, 3, product2.getPrice(), null, product2, null);
        item2.setTotalPrice(); // 300.00

        // Act
        cart.addItem(item1);
        cart.addItem(item2);
        Cart savedCart = cartRepository.save(cart);

        // Assert
        assertThat(savedCart.getTotalAmount()).isEqualByComparingTo(new BigDecimal("900.00"));
        assertThat(savedCart.getItems()).hasSize(2);
    }

    @Test
    void deleting_cart_should_cascade_remove_associated_items() {
        // Arrange
        Category cat = categoryRepository.save(new Category("Clothes"));
        Product product = productRepository.save(new Product("T-shirt", "Zara", new BigDecimal("400.00"), 10, "Summer", cat));

        Cart cart = new Cart();
        CartItem item = new CartItem(null, 1, product.getPrice(), null, product, null);
        item.setTotalPrice();
        cart.addItem(item);

        cartRepository.save(cart);

        // Act
        cartRepository.delete(cart);

        // Assert
        assertFalse(cartRepository.existsById(cart.getId()));

        assertFalse(cartItemRepository.existsById(item.getId()));
    }
}