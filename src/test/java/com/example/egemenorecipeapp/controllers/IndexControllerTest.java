package com.example.egemenorecipeapp.controllers;

import com.example.egemenorecipeapp.model.Recipe;
import com.example.egemenorecipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@Slf4j
public class IndexControllerTest {

   private IndexController indexController;

   @Mock
   private RecipeService recipeService;

   @Mock
   private Model model;



    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.indexController=new IndexController(recipeService);
    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc=MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }




    @Test
    public void getIndexPage() {
     //given

        Set<Recipe> recipeSet=new HashSet<>();
        Recipe recipe1=new Recipe();
        recipe1.setDescription("recipe1");
        Recipe recipe2=new Recipe();
        recipe2.setDescription("recipe2");
        Recipe recipe3=new Recipe();
        recipe3.setDescription("recipe3");


        recipeSet.add(recipe1);
        recipeSet.add(recipe2);
        recipeSet.add(recipe3);

        System.out.println(recipeSet.size());
        when(recipeService.getRecipes()).thenReturn(recipeSet);
        ArgumentCaptor<Set<Recipe>> argumentCaptor=ArgumentCaptor.forClass(Set.class);

     //when

        String view= indexController.getIndexPage(model);

     //then

        assertEquals(3,recipeService.getRecipes().size());
        assertEquals("index",view);

        verify(recipeService,times(2)).getRecipes();
        verify(model,times(1)).addAttribute(eq("recipes"),argumentCaptor.capture()); //matcher anySet() reqired eq //put recipeService.getRecipes() to test same result

        Set<Recipe> set= argumentCaptor.getValue();
        assertEquals(3,set.size());
    }
}