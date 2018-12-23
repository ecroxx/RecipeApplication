package com.example.egemenorecipeapp.controllers;

import com.example.egemenorecipeapp.model.Category;
import com.example.egemenorecipeapp.model.UnitOfMeasure;
import com.example.egemenorecipeapp.repositories.CategoryRepository;
import com.example.egemenorecipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
Created By Egemen Ozkan
 */

@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"","/","index","recipe"})
    public String getIndexPage(){

        Optional<Category> categoryOptional=categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional=unitOfMeasureRepository.findByDescription("Teaspoon");

        System.out.println("Category ID is "+categoryOptional.get().getId());
        System.out.println("UOM ID is " + unitOfMeasureOptional.get().getId());

        return "index";
    }
}
