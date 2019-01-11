package com.example.egemenorecipeapp.services;

import com.example.egemenorecipeapp.commands.RecipeCommand;
import com.example.egemenorecipeapp.converters.RecipeCommandToRecipe;
import com.example.egemenorecipeapp.converters.RecipeToRecipeCommand;
import com.example.egemenorecipeapp.exceptions.NotFoundException;
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
    public void getRecipeByIdTest() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById(1L);

        assertNotNull("Null recipe returned", recipeReturned);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test(expected =NotFoundException.class )
    public void getRecipeByIdTestNotFound() throws Exception {

        Optional<Recipe> recipeOptional = Optional.empty();

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById(1L);

        //should go boom
    }

    @Test
    public void getRecipeCommandByIdTest() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        when(recipeToRecipeCommand.convert(any())).thenReturn(recipeCommand);

        RecipeCommand commandById = recipeService.findCommandById(1L);

        assertNotNull("Null recipe returned", commandById);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }


    @Test
    public void deleteByIdTest() throws Exception{
        //given
        Long deleteId=Long.valueOf(1L);

        //when
        recipeRepository.deleteById(deleteId);

        //then
        verify(recipeRepository,times(1)).deleteById(anyLong());
    }
}