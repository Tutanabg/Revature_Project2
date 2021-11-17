package com.revature.repositories;

import com.revature.models.MenuItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepo extends CrudRepository<MenuItem, Integer> {
}
