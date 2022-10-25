package com.toko.online.service;

import com.toko.online.model.entity.Product;
import com.toko.online.respository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProduct(){
        List<Product> product = productRepository.findAll();
        return product;
    }

    public void saveProduct(Product product){
        productRepository.save(product);

    }
    public void updateProduct(Product product, int id){
        Optional<Product> dataProduct = productRepository.findById(id);
        dataProduct.get().setNamaProduct(product.getNamaProduct());
        productRepository.save(dataProduct.get());
    }
    public void deleteProduct(int id){
        Optional<Product> dataProduct = productRepository.findById(id);

        productRepository.delete(dataProduct.get());
    }

}