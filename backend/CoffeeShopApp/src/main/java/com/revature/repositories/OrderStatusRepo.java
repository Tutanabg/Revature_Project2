package com.revature.repositories;


import com.revature.models.OrderStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepo extends CrudRepository<OrderStatus, Integer> {
}
