package com.example.egemenorecipeapp.controllers;

import com.example.egemenorecipeapp.commands.RecipeCommand;
import com.example.egemenorecipeapp.model.Recipe;
import com.example.egemenorecipeapp.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RecipeControllerTest {

    RecipeController recipeController;

    @Mock
    RecipeService recipeService;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeController=new RecipeController(recipeService);
        mockMvc= MockMvcBuilders.standaloneSetup(recipeController).build();

    }

    @Test
    public void getRecipeTest()throws Exception{
        Recipe recipe=new Recipe();
        recipe.setId(1l);

        when(recipeService.findById(anyLong())).thenReturn(recipe);

        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"))
                 .andExpect(model().attributeExists("recipe"));

    }
    @Test
    public void getNewRecipeFormTest() throws Exception{
        mockMvc.perform(get("/recipe/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void postNewRecipeFormTest() throws Exception{
        RecipeCommand recipeCommand=new RecipeCommand();
        recipeCommand.setId(4L);

        when(recipeService.saveRecipeCommand(any())).thenReturn(recipeCommand);
        mockMvc.perform(post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id","")
                .param("description","description")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/4/show"));
        verify(recipeService,times(1)).saveRecipeCommand(any());

    }

    @Test
    public void updateRecipeFormTest() throws Exception{
        RecipeCommand recipeCommand=new RecipeCommand();
        recipeCommand.setId(5L);
        when(recipeService.findCommandById(any())).thenReturn(recipeCommand);

        mockMvc.perform(get("/recipe/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"))
                .andExpect(model().attribute("recipe",recipeCommand));
        verify(recipeService,times(1)).findCommandById(any());

    }
    @Test
    public void deleteRecipeTest() throws Exception {
       mockMvc.perform(get("/recipe/1/delete"))
               .andExpect(status().is3xxRedirection())
               .andExpect(view().name("redirect:/"));
       verify(recipeService,times(1)).deleteById(anyLong());

    }
}