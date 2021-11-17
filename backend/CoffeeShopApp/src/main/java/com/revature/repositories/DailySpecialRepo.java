package com.revature.repositories;

import com.revature.models.DailySpecial;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailySpecialRepo extends CrudRepository<DailySpecial, Integer> {
}
