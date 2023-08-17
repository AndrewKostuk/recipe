package by.bsuir.andrei.recipe.controllers;

import by.bsuir.andrei.recipe.domain.Recipe;
import by.bsuir.andrei.recipe.services.RecipeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IndexControllerTest {

    private IndexController indexController;

    @Mock
    private RecipeService recipeService;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        indexController = new IndexController(recipeService);
    }

    @Test
    void getIndex() {
        //given
        final var recipes = List.of(new Recipe());
        when(recipeService.getAll()).thenReturn(recipes);
        String expected = "index";
        ArgumentCaptor<List<Recipe>> captor = ArgumentCaptor.forClass(List.class);

        //when
        String actual = indexController.getIndex(model);

        //then
        Assertions.assertEquals(expected, actual);
        verify(recipeService, times(1)).getAll();
        verify(model, times(1)).addAttribute(eq("recipes"), captor.capture());
        Assertions.assertEquals(1, captor.getValue().size());
    }
}