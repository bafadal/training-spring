package com.toko.online.repo;

import com.toko.online.model.entity.Product;
import com.toko.online.respository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.AssertionsForClassTypes.*;
import org.junit.jupiter.api.Assertions;

@SpringBootTest
public class ProductRepoTest {

  @Autowired
  private ProductRepository repository;

  @Test
  void getProductByIdTestPositiveCase() {
      Product product = new Product();
      product.setId(1);
      repository.save(product);
      Product result = repository.getProductById(1);
      Assertions.assertNotNull(result);
  }

  @Test
  void getProductByIdTestNegativeCase() {
    Product product = new Product();
    product.setId(2);
    repository.save(product);
    Product result = repository.getProductById(3);
    Assertions.assertNull(result);
  }



}
