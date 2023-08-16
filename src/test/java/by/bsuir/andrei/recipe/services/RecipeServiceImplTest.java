package by.bsuir.andrei.recipe.services;

import by.bsuir.andrei.recipe.domain.Recipe;
import by.bsuir.andrei.recipe.repositories.RecipeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecipeServiceImplTest {

    private RecipeServiceImpl recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    void getAll() {
        List<Recipe> recipeList = List.of(new Recipe());
        when(recipeRepository.findAll()).thenReturn(recipeList);
        Assertions.assertEquals(1, recipeService.getAll().size());
        verify(recipeRepository, times(1)).findAll();
    }
}