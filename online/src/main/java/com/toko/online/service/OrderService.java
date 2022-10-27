package com.toko.online.service;

import com.toko.online.model.dto.CustomerOrder;
import com.toko.online.model.entity.Order;
import com.toko.online.model.entity.Product;
import com.toko.online.respository.OrderRepository;
import com.toko.online.respository.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private ProductRepository productRepository;

  public void saveOrder(Order order){
      Optional<Product> product = productRepository.findById(order.getProduct().getId());
      int totalHarga = order.getJumlahProduct() * product.get().getHargaProduct();
      order.setTotalHarga(totalHarga);
      orderRepository.save(order);
  }

  public List<Order> getOrder(){
      return orderRepository.findAll();
  }

  public CustomerOrder getCustomerOrder(int productId){
    Order order = orderRepository.getCustomerOrder(productId);
    CustomerOrder customerOrder = new CustomerOrder();
    customerOrder.setNamaProduct(order.getProduct().getNamaProduct());
    customerOrder.setJumlahProduct(order.getJumlahProduct());
    customerOrder.setKodeProduct(order.getProduct().getKodeProduct());
    customerOrder.setTotalHarga(order.getTotalHarga());
    return customerOrder;
  }

}
