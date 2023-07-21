package by.bsuir.andrei.recipe.services;

import by.bsuir.andrei.recipe.domain.Recipe;
import by.bsuir.andrei.recipe.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<Recipe> getAll() {
        return recipeRepository.findAll();
    }
}
