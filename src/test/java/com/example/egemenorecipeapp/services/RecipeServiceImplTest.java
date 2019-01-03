package com.example.egemenorecipeapp.services;

import com.example.egemenorecipeapp.converters.RecipeCommandToRecipe;
import com.example.egemenorecipeapp.converters.RecipeToRecipeCommand;
import com.example.egemenorecipeapp.model.Recipe;
import com.example.egemenorecipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@Slf4j

public class RecipeServiceImplTest {

    RecipeService recipeService;

   @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }


    @Test
    public void getRecipesTest() {
        //given
        Recipe recipe=new Recipe();
        Set<Recipe> recipes=new HashSet<>();
        recipes.add(recipe);

        log.debug("test code1");
        when(recipeService.getRecipes()).thenReturn(recipes);
        log.debug("test code2");

        //when
        Set<Recipe> recipeSet= recipeService.getRecipes();

        //then
        assertEquals(recipeSet.size(),1);
        verify(recipeRepository,times(1)).findAll();
    }


    @Test
    public void findByIdTest() throws Exception {
        Recipe recipe=new Recipe();
        recipe.setId(1L);
        Optional<Recipe> optionalRecipe=Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);
        Recipe recipe1=recipeService.findById(1L);

        assertNotNull(recipe1);

        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository,never()).findAll();
    }
}