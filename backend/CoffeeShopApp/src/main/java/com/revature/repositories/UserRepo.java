package com.revature.repositories;

import com.revature.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {
    User findByUsernameAndPassword(String username, String password);

    User findByFirstNameAndLastName(String firstName, String lastName);
}
