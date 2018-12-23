package com.example.egemenorecipeapp.repositories;

import com.example.egemenorecipeapp.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Long> {
}
