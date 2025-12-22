package com.sai.inventory.controller;

import com.sai.inventory.configuration.JwtAuthFilter;
import com.sai.inventory.dao.MasterDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private MasterDao masterDao;

    Logger logger = LoggerFactory.getLogger(JwtAuthFilter.class);


    @GetMapping("/test")
    public String test() {
        String s = "Version : RUNNING:VER-1.0_22/Jan/2025|21:02";
        logger.info(s);
        return s;
    }

    @GetMapping("/itemCodeCount")
    public String itemCodeCount(@RequestParam("itemCode") String itemCode, @RequestParam("orgId") Long orgId) {
        return String.valueOf(masterDao.getTotalQuantityOfItemInOrg(itemCode, orgId));
    }
}
