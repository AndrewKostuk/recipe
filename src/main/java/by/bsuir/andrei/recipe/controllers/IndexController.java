package by.bsuir.andrei.recipe.controllers;

import by.bsuir.andrei.recipe.domain.Category;
import by.bsuir.andrei.recipe.domain.UnitOfMeasure;
import by.bsuir.andrei.recipe.repositories.CategoryRepository;
import by.bsuir.andrei.recipe.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndex() {
        Optional<Category> optionalCategory = categoryRepository.findByDescription("Fast food");
        Optional<UnitOfMeasure> optionalUnitOfMeasure = unitOfMeasureRepository.findByDescription("Ounce");

        optionalCategory.ifPresent(category -> System.out.println(category.getId()));
        optionalUnitOfMeasure.ifPresent(unit -> System.out.println(unit.getId()));
        System.out.println("3к6--------------------42------_???????????????????/");
        return "index";
    }
}
