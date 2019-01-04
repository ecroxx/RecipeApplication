package com.example.egemenorecipeapp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;

    @Lob
    private String directions;



    @OneToMany(cascade = CascadeType.ALL,mappedBy = "recipe",orphanRemoval = true)
    private Set<Ingredient> ingredients=new HashSet<>();

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @Lob
    private Byte[] image;


    @OneToOne(cascade = CascadeType.ALL,mappedBy ="recipe",orphanRemoval = true)
    private Notes notes;

    @ManyToMany
    @JoinTable (name = "recipe_cathegories",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name="category_id")
    )
    private Set<Category> categories=new HashSet<>();

// Checks for null notes object
    public void setNotes(Notes notes) {
        if (notes != null) {
            this.notes = notes;
            notes.setRecipe(this);
        }
    }

}
