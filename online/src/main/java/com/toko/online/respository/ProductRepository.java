package com.toko.online.respository;

import com.toko.online.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
  Product getProductByNamaProduct(String namaProduct);
  Product getProductByJenisProduct(String jenisProduct);
}
