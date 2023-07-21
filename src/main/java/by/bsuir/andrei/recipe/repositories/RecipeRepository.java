package by.bsuir.andrei.recipe.repositories;

import by.bsuir.andrei.recipe.domain.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    List<Recipe> findAll();
}
