package com.revature.repositories;


import com.revature.models.PaymentType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentTypeRepo extends CrudRepository<PaymentType, Integer> {
}
