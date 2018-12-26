package com.example.egemenorecipeapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"recipes"})
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToMany (cascade = CascadeType.ALL,mappedBy = "categories",fetch = FetchType.EAGER)
    private Set<Recipe> recipes=new HashSet<>();

}
