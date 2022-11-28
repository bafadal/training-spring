package com.toko.online.controllermvc;

import com.toko.online.model.entity.Product;
import com.toko.online.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

}
