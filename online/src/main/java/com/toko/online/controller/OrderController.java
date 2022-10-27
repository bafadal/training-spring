package com.toko.online.controller;

import com.toko.online.model.entity.Order;
import com.toko.online.service.OrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

  @Autowired
  private OrderService orderService;

  @PostMapping("order")
  public ResponseEntity addOrder(@RequestBody Order order){
        orderService.saveOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body("data berhasil disimpan");
  }

  @PostMapping("get-customer-order")
  public ResponseEntity getCustomerOrder(@RequestParam("productId") int productId){
    return ResponseEntity.status(HttpStatus.CREATED).body(orderService.getCustomerOrder(productId));
  }

  @GetMapping("order")
  public ResponseEntity<List<Order>> getOrder(){
    return ResponseEntity.ok(orderService.getOrder());
  }
}
