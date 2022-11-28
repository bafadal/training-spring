package com.toko.online.controller;

import com.toko.online.model.entity.Product;
import com.toko.online.service.ProductService;
import javax.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public ResponseEntity getProduct(){
        List<Product> product = productService.getProduct();
        if (product.size() > 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(product);
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping("/product")
    public String addProduct(@RequestBody Product product){
        productService.saveProduct(product);
        return "data berhasil disimpan";
    }

    @PutMapping("/product")
    public String updateProduct(@RequestBody Product product,@RequestParam("id") int id){
        productService.updateProduct(product, id);
        return "Data berhasil di update";
    }

    @DeleteMapping("/product")
    public String deleteProduct(@RequestParam("id") int id){
        productService.deleteProduct(id);
        return "Data berhasil di hapus";
    }


}
