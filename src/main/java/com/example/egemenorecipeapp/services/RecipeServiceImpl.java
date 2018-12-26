package com.example.egemenorecipeapp.services;

import com.example.egemenorecipeapp.model.Recipe;
import com.example.egemenorecipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
     private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }


    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipes=new HashSet<>();
         recipeRepository.findAll().forEach(recipes::add);
         log.debug("It is Receipe Service Impl");
         return recipes;
    }
}
