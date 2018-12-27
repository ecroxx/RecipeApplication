package com.example.egemenorecipeapp.services;

import com.example.egemenorecipeapp.model.Recipe;
import com.example.egemenorecipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@Slf4j
public class RecipeServiceImplTest {

    RecipeService recipeService;

   @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.recipeService=new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void getRecipes() {
        Recipe recipe=new Recipe();
        Set<Recipe> recipes=new HashSet<>();
        recipes.add(recipe);

        log.debug("test code1");

        when(recipeService.getRecipes()).thenReturn(recipes);

        log.debug("test code2");

        Set<Recipe> recipeSet= recipeService.getRecipes();
        assertEquals(recipeSet.size(),1);

        verify(recipeRepository,times(1)).findAll();
    }
}