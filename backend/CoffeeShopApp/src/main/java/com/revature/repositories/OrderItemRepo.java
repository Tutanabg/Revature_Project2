package com.revature.repositories;


import com.revature.models.Order;
import com.revature.models.OrderItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepo extends CrudRepository<OrderItem, Integer> {
}
