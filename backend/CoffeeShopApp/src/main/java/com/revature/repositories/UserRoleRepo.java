package com.revature.repositories;

import com.revature.models.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepo extends CrudRepository<UserRole, Integer> {
}
