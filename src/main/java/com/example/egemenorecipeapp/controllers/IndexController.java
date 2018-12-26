package com.example.egemenorecipeapp.controllers;

import com.example.egemenorecipeapp.repositories.CategoryRepository;
import com.example.egemenorecipeapp.repositories.UnitOfMeasureRepository;
import com.example.egemenorecipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
Created By Egemen Ozkan
 */
@Slf4j
@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeService recipeService;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeService recipeService) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/","index","recipe"})
    public String getIndexPage(Model model){
        log.debug("Index Page is Loading");
        model.addAttribute("recipes",recipeService.getRecipes());
        /*
        Optional<Category> categoryOptional=categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional=unitOfMeasureRepository.findByDescription("Teaspoon");

        System.out.println("Category ID is "+categoryOptional.get().getId());
        System.out.println("UOM ID is " + unitOfMeasureOptional.get().getId());
       */
        return "index";
    }
}
