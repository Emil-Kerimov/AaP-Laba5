package org.example.lab2db;

import org.example.lab2db.models.Category;
import org.example.lab2db.models.Product;
import org.example.lab2db.repo.CategoryRepository;
import org.example.lab2db.repo.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ProductRepositoryIntegrationTest extends BaseContainerConfig {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void product_can_be_saved_and_found_by_name() {
        Category category = categoryRepository.save(new Category("Gadgets"));
        Product product = new Product("test", "Brand A", new BigDecimal("5000.00"), 5, "Fly high", category);

        productRepository.save(product);
        List<Product> foundProducts = productRepository.findByName("test");

        // Assert
        assertThat(foundProducts).isNotEmpty();
        assertThat(foundProducts.get(0).getName()).isEqualTo("test");
    }

    @Test
    void products_can_be_found_by_category_name() {
        // Arrange
        Category cat_home = categoryRepository.save(new Category("Home Goods"));
        Category cat_kitchen = categoryRepository.save(new Category("Kitchen"));

        productRepository.save(new Product("Vase", "Elegant", new BigDecimal("100"), 5, "Decoration", cat_home));
        productRepository.save(new Product("Towel", "Soft", new BigDecimal("50"), 10, "Textile", cat_home));
        productRepository.save(new Product("Mixer", "Bosch", new BigDecimal("1500"), 20, "Cooking", cat_kitchen));

        // Act
        List<Product> homeProducts = productRepository.findByCategoryName("Home Goods");

        // Assert
        assertThat(homeProducts).hasSize(2);
        assertThat(homeProducts).extracting(Product::getName).containsExactlyInAnyOrder("Vase", "Towel");
    }
}