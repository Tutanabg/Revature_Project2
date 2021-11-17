package com.revature.services;

import com.revature.models.Picture;

import java.util.List;

public interface PictureService {

    public Picture addPicture(Picture a);
    public Picture getPicture(int id);
    public List<Picture> getAllPictures();
    public Picture updatePicture(Picture change);
    public boolean deletePicture(int id);
}
