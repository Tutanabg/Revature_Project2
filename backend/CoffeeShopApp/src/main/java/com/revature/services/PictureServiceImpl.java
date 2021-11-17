package com.revature.services;

import com.revature.models.Picture;
import com.revature.repositories.PictureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureServiceImpl implements PictureService{

    @Autowired
    PictureRepo pr;


    @Override
    public Picture addPicture(Picture p) {
        return pr.save(p);
    }

    @Override
    public Picture getPicture(int id) {
        return pr.findById(id).get();
    }

    @Override
    public List<Picture> getAllPictures() {
        return (List<Picture>) pr.findAll();
    }

    @Override
    public Picture updatePicture(Picture change) {
        return pr.save(change);
    }

    @Override
    public boolean deletePicture(int id) {
        try{
            pr.deleteById(id);
            return true;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }
}
