package by.bsuir.andrei.recipe.controllers;

import by.bsuir.andrei.recipe.domain.Recipe;
import by.bsuir.andrei.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndex(Model model) {
        log.debug("I'm in Controller");
        List<Recipe> recipes = recipeService.getAll();
        model.addAttribute("recipes", recipes);
        return "index";
    }
}
