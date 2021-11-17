package com.revature.repositories;

import com.revature.models.IngredientOrderItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientOrderItemRepo extends CrudRepository<IngredientOrderItem, Integer> {
}
