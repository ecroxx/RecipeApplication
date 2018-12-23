package com.example.egemenorecipeapp.repositories;

import com.example.egemenorecipeapp.model.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe,Long> {
}
