package com.example.egemenorecipeapp.services;

import com.example.egemenorecipeapp.commands.RecipeCommand;
import com.example.egemenorecipeapp.model.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe findById(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand command);
}
