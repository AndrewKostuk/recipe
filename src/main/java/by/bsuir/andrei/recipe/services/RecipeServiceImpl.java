package by.bsuir.andrei.recipe.services;

import by.bsuir.andrei.recipe.domain.Recipe;
import by.bsuir.andrei.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<Recipe> getAll() {
        log.debug("I'm in the Recipe Service");
        return recipeRepository.findAll();
    }
}
