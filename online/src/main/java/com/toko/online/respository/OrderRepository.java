package com.toko.online.respository;

import com.toko.online.model.dto.CustomerOrder;
import com.toko.online.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Integer> {
  @Query(value = " select * from orders o join product p on o.product_id = p.product_id where p.product_id = ?1"
  , nativeQuery = true)
  public Order getCustomerOrder(int productId);
}
