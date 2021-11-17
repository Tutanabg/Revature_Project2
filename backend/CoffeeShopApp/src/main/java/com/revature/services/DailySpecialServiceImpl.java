package com.revature.services;

import com.revature.models.DailySpecial;
import com.revature.repositories.DailySpecialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailySpecialServiceImpl implements DailySpecialService{

    @Autowired
    DailySpecialRepo dsr;

    @Override
    public List<DailySpecial> getAllDailySpecials() {
        return (List<DailySpecial>) dsr.findAll();
    }
}
