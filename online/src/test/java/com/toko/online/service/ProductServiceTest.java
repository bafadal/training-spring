package com.toko.online.service;

import com.toko.online.model.entity.Product;
import com.toko.online.respository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ProductServiceTest {
  @InjectMocks
  private ProductService productService;

  @Mock
  private ProductRepository productRepository;

  @Test
  void testSaveService()
  {
    Product product = new Product();
    product.setId(3);
    productService.saveProduct(product);

  }

  @Test
  void testGetByIdService()
  {
//    when(productService.getProductById(3)).thenReturn(2);
  }

  @Test
  void testAllService()
  {
    Product product = new Product();
    product.setId(1);
    product.setNamaProduct("Kipas angin");
    List<Product> list = new ArrayList<>();
    list.add(product);
    when(productService.getProduct()).thenReturn(list);
  }


}
