package com.example.egemenorecipeapp.services;

import com.example.egemenorecipeapp.commands.RecipeCommand;
import com.example.egemenorecipeapp.converters.RecipeCommandToRecipe;
import com.example.egemenorecipeapp.converters.RecipeToRecipeCommand;
import com.example.egemenorecipeapp.exceptions.NotFoundException;
import com.example.egemenorecipeapp.model.Recipe;
import com.example.egemenorecipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }


    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipes=new HashSet<>();
         recipeRepository.findAll().forEach(recipes::add);
         log.debug("It is Receipe Service Impl");
         return recipes;
    }
    @Override
    public Recipe findById(Long id){
        Optional<Recipe> optionalRecipe= recipeRepository.findById(id);
        if(!optionalRecipe.isPresent()){
            throw new NotFoundException("Recipe Not Found");
        }
        return optionalRecipe.get();
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved RecipeId:" + savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long id){
        return recipeToRecipeCommand.convert(findById(id));
    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }
}
