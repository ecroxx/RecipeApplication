package com.example.egemenorecipeapp.services;

import com.example.egemenorecipeapp.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long RecipeId, Long IngredientId);
    IngredientCommand saveIngredientCommand(IngredientCommand command);

}
