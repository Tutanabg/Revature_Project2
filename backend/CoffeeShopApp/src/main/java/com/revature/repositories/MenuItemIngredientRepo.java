package com.revature.repositories;

import com.revature.models.MenuItemIngredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemIngredientRepo extends CrudRepository<MenuItemIngredient, Integer> {
}
