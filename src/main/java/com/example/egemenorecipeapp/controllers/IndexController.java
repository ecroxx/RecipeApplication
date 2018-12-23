package com.example.egemenorecipeapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
Created By Egemen Ozkan
 */

@Controller
public class IndexController {
    @RequestMapping({"","/","index","recipe"})
    public String getIndexPage(){
        return "index";
    }
}
