package com.toko.online.controllermvc;

import com.toko.online.model.entity.Product;
import com.toko.online.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class ProductUIController {

  @Autowired
  private ProductService productService;

  @GetMapping("/")
  public String getProduct(Model model) {
        model.addAttribute("product", productService.getProduct());
        return "index";
  }

  @GetMapping("/add-product")
  public String addProduct(Model model) {
    model.addAttribute("product", new Product());
    return "add_product";
  }

  @PostMapping("/add-product")
  public String saveProduct(@ModelAttribute("product") Product product) {
    productService.saveProduct(product);
    return "redirect:/";
  }

  @GetMapping("/edit-product/{id}")
  public String getDataEdit(@PathVariable("id") int productId, Model model) {
    model.addAttribute("product", productService.getProductById(productId));
    return "edit_product";
  }

  @PostMapping("/update-product/{id}")
  public String editData(@PathVariable("id") int productId, @ModelAttribute("product") Product product, Model model) {
    productService.updateProduct(product, productId);
    model.addAttribute("product", productService.getProduct());
    return "redirect:/";
  }

  @GetMapping("/delete-product/{id}")
  public String deleteProduct(@PathVariable("id") int productId,Model model){
    productService.deleteProduct(productId);
    model.addAttribute("product", productService.getProduct());
    model.addAttribute("message", "Data deleted successfully");
    return "response";
  }



}
