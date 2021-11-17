package com.revature.repositories;

import com.revature.models.ItemCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCategoryRepo extends CrudRepository<ItemCategory, Integer> {
}
