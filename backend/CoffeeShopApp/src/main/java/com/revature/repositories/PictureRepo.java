package com.revature.repositories;

import com.revature.models.Picture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PictureRepo extends CrudRepository<Picture, Integer> {


}
