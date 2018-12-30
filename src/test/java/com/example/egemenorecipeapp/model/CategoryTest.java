package com.example.egemenorecipeapp.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

//@RunWith(SpringRunner.class)
public class CategoryTest {

    Category category;




    @Before
    public void setUp(){
        category= new Category();
    }

    @Test
    public void getId() {

        Long id= 4l;
        category.setId(id);
    assertEquals(id,category.getId());

    }

    @Test
    public void getDescription() {
        category.setDescription("english");
        assertEquals("english",category.getDescription());
    }

    @Test
    public void getRecipes() {
       // Set<Recipe> recipeSet=new HashSet<>(Arrays.asList(new Recipe()));
       //   category.setRecipes(recipeSet);
    }
}