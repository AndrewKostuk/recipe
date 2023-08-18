package by.bsuir.andrei.recipe.repositories;

import by.bsuir.andrei.recipe.domain.UnitOfMeasure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository repository;

    @Test
    void findByDescriptionTeaspoon() {
        Optional<UnitOfMeasure> teaspoon = repository.findByDescription("Teaspoon");
        Assertions.assertTrue(teaspoon.isPresent());
        Assertions.assertEquals("Teaspoon", teaspoon.get().getDescription());
    }

    @Test
    void findByDescriptionCup() {
        Optional<UnitOfMeasure> teaspoon = repository.findByDescription("Cup");
        Assertions.assertTrue(teaspoon.isPresent());
        Assertions.assertEquals("Cup", teaspoon.get().getDescription());
    }
}