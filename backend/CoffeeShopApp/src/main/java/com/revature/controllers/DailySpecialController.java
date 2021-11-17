package com.revature.controllers;

import com.revature.models.DailySpecial;
import com.revature.services.DailySpecialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class DailySpecialController {

    @Autowired
    DailySpecialService dss;

    @GetMapping("/dailySpecials")
    public List<DailySpecial> getAllDailySpecials() {
        return dss.getAllDailySpecials();
    }
}
