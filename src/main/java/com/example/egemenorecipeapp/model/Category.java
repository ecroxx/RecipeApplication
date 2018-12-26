package com.example.egemenorecipeapp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToMany (cascade = CascadeType.ALL,mappedBy = "categories",fetch = FetchType.EAGER)
    private Set<Recipe> recipes=new HashSet<>();

}
