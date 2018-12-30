package com.example.egemenorecipeapp.repositories;

import com.example.egemenorecipeapp.model.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;


    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void findByDescription() {
        Optional<UnitOfMeasure> unitOfMeasure=unitOfMeasureRepository.findByDescription("Teaspoon");
        assertEquals("Teaspoon",unitOfMeasure.get().getDescription());
    }
    @Test
    public void findByDescriptionTablespoon() {
        Optional<UnitOfMeasure> unitOfMeasure=unitOfMeasureRepository.findByDescription("Tablespoon");
        assertEquals("Tablespoon",unitOfMeasure.get().getDescription());
    }
    @Test
    public void findByDescriptionCup() {
        Optional<UnitOfMeasure> unitOfMeasure=unitOfMeasureRepository.findByDescription("Cup");
        assertEquals("Cup",unitOfMeasure.get().getDescription());
    }
    @Test
    public void findByDescriptionPinch() {
        Optional<UnitOfMeasure> unitOfMeasure=unitOfMeasureRepository.findByDescription("Pinch");
        assertEquals("Pinch",unitOfMeasure.get().getDescription());
    }
    @Test
    public void findByDescriptionOunce() {
        Optional<UnitOfMeasure> unitOfMeasure=unitOfMeasureRepository.findByDescription("Ounce");
        assertEquals("Ounce",unitOfMeasure.get().getDescription());
    }
    @Test
    public void findByDescriptionEach() {
        Optional<UnitOfMeasure> unitOfMeasure=unitOfMeasureRepository.findByDescription("Each");
        assertEquals("Each",unitOfMeasure.get().getDescription());
    }
    @Test
    public void findByDescriptionDash() {
        Optional<UnitOfMeasure> unitOfMeasure=unitOfMeasureRepository.findByDescription("Dash");
        assertEquals("Dash",unitOfMeasure.get().getDescription());
    }
    @Test
    public void findByDescriptionPint() {
        Optional<UnitOfMeasure> unitOfMeasure=unitOfMeasureRepository.findByDescription("Pint");
        assertEquals("Pint",unitOfMeasure.get().getDescription());
    }
}