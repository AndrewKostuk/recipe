package by.bsuir.andrei.recipe.bootstrap;

import by.bsuir.andrei.recipe.domain.*;
import by.bsuir.andrei.recipe.repositories.CategoryRepository;
import by.bsuir.andrei.recipe.repositories.RecipeRepository;
import by.bsuir.andrei.recipe.repositories.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeRepository recipeRepository;

    public DataLoader(CategoryRepository categoryRepository,
                      UnitOfMeasureRepository unitOfMeasureRepository,
                      RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        createGuacamole();
        createChicken();
    }

    private void createGuacamole() {
        Optional<UnitOfMeasure> teaspoon = unitOfMeasureRepository.findByDescription("Teaspoon");
        Optional<UnitOfMeasure> tablespoon = unitOfMeasureRepository.findByDescription("Tablespoon");
        Optional<UnitOfMeasure> pinch = unitOfMeasureRepository.findByDescription("Pinch");

        Optional<Category> mexican = categoryRepository.findByDescription("Mexican");

        Recipe guacamole = new Recipe();
        guacamole.setDescription("""
                The word "guacamole" and the dip, are both originally from Mexico,\s
                where avocados have been cultivated for thousands of years. The name is derived from\s
                two Aztec Nahuatl words—ahuacatl (avocado) and molli (sauce).""");
        guacamole.setPrepTime(10);
        guacamole.setCookTime(0);
        guacamole.setServings(4);
        guacamole.setSource("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole.setDirections("Cut the avocados. Mash the avocado flesh. Add the remaining ingredients to taste. \n" +
                "Serve immediately");
        guacamole.setDifficulty(Difficulty.EASY);
        Notes guacamoleNote = new Notes();
        guacamoleNote.setRecipe(guacamole);
        guacamoleNote.setRecipeNotes("Chilling tomatoes hurts their flavor. So, if you want to add chopped tomato \n" +
                "to your guacamole, add it just before serving.");
        guacamole.setNotes(guacamoleNote);

        Set<Ingredient> guacamoleIngredients = createGuacamoleIngredients(guacamole, teaspoon, tablespoon, pinch);
        guacamole.setIngredients(guacamoleIngredients);

        Set<Category> guacamoleCategories = new HashSet<>();
        mexican.ifPresent(guacamoleCategories::add);
        guacamole.setCategories(guacamoleCategories);

        recipeRepository.save(guacamole);
    }

    private Set<Ingredient> createGuacamoleIngredients(Recipe guacamole,
                                                       Optional<UnitOfMeasure> teaspoon,
                                                       Optional<UnitOfMeasure> tablespoon,
                                                       Optional<UnitOfMeasure> pinch) {
        Set<Ingredient> guacamoleIngredients = new HashSet<>();
        Ingredient avocado = new Ingredient();
        avocado.setDescription("avocado");
        avocado.setAmount(BigDecimal.valueOf(2));
        avocado.setRecipe(guacamole);
        guacamoleIngredients.add(avocado);

        Ingredient salt = new Ingredient();
        salt.setDescription("salt");
        salt.setAmount(BigDecimal.valueOf((double) 1 / 4));
        salt.setRecipe(guacamole);
        teaspoon.ifPresent(salt::setUom);
        guacamoleIngredients.add(salt);

        Ingredient lemonJuice = new Ingredient();
        lemonJuice.setDescription("lemon juice");
        lemonJuice.setAmount(BigDecimal.valueOf(1));
        lemonJuice.setRecipe(guacamole);
        tablespoon.ifPresent(lemonJuice::setUom);
        guacamoleIngredients.add(lemonJuice);

        Ingredient onion = new Ingredient();
        onion.setDescription("onion");
        onion.setAmount(BigDecimal.valueOf(2));
        onion.setRecipe(guacamole);
        tablespoon.ifPresent(onion::setUom);
        guacamoleIngredients.add(onion);

        Ingredient chilis = new Ingredient();
        chilis.setDescription("chilis");
        chilis.setAmount(BigDecimal.valueOf(1));
        chilis.setRecipe(guacamole);
        guacamoleIngredients.add(chilis);

        Ingredient cilantro = new Ingredient();
        cilantro.setDescription("cilantro");
        cilantro.setAmount(BigDecimal.valueOf(2));
        cilantro.setRecipe(guacamole);
        tablespoon.ifPresent(cilantro::setUom);
        guacamoleIngredients.add(cilantro);

        Ingredient pepper = new Ingredient();
        pepper.setDescription("pepper");
        pepper.setAmount(BigDecimal.valueOf(1));
        pepper.setRecipe(guacamole);
        pinch.ifPresent(pepper::setUom);
        guacamoleIngredients.add(pepper);

        Ingredient tomato = new Ingredient();
        tomato.setDescription("tomato");
        tomato.setAmount(BigDecimal.valueOf((double) 1 / 2));
        tomato.setRecipe(guacamole);
        guacamoleIngredients.add(tomato);

        Ingredient radish = new Ingredient();
        radish.setDescription("radish");
        radish.setRecipe(guacamole);
        guacamoleIngredients.add(radish);

        Ingredient tortillaChips = new Ingredient();
        tortillaChips.setDescription("tortilla chips");
        tortillaChips.setRecipe(guacamole);
        guacamoleIngredients.add(tortillaChips);

        return guacamoleIngredients;
    }

    private void createChicken() {
        Optional<UnitOfMeasure> teaspoon = unitOfMeasureRepository.findByDescription("Teaspoon");
        Optional<UnitOfMeasure> tablespoon = unitOfMeasureRepository.findByDescription("Tablespoon");
        Optional<UnitOfMeasure> pound = unitOfMeasureRepository.findByDescription("Pound");
        Optional<UnitOfMeasure> ounce = unitOfMeasureRepository.findByDescription("Ounce");
        Optional<UnitOfMeasure> pint = unitOfMeasureRepository.findByDescription("Pint");
        Optional<UnitOfMeasure> cup = unitOfMeasureRepository.findByDescription("Cup");

        Optional<Category> american = categoryRepository.findByDescription("American");

        Recipe chicken = new Recipe();
        chicken.setDescription("Spicy grilled chicken tacos! Quick marinade, then grill. Ready in about 30 minutes. \n" +
                "Great for a quick weeknight dinner, backyard cookouts, and tailgate parties.");
        chicken.setPrepTime(20);
        chicken.setCookTime(15);
        chicken.setServings(6);
        chicken.setSource("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        chicken.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        chicken.setDirections("Prepare the grill. Make the marinade and coat the chicken. Grill the chicken. \n" +
                "Thin the sour cream with milk. Assemble the tacos. Warm the tortillas");
        chicken.setDifficulty(Difficulty.MODERATE);
        Notes chickenNote = new Notes();
        chickenNote.setRecipe(chicken);
        chickenNote.setRecipeNotes("""
                Look for ancho chile powder with the Mexican ingredients at your grocery store,\s
                on buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano,\s
                and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)""");
        chicken.setNotes(chickenNote);

        Set<Ingredient> chickenIngredients = new HashSet<>();
        chickenIngredients.addAll(createChickenIngredients(chicken, teaspoon, tablespoon, pound));
        chickenIngredients.addAll(createChickenServeIngredients(chicken, ounce, pint, cup));
        chicken.setIngredients(chickenIngredients);

        Set<Category> chickenCategories = new HashSet<>();
        american.ifPresent(chickenCategories::add);
        chicken.setCategories(chickenCategories);

        recipeRepository.save(chicken);
    }

    private Set<Ingredient> createChickenIngredients(Recipe chicken,
                                                     Optional<UnitOfMeasure> teaspoon,
                                                     Optional<UnitOfMeasure> tablespoon,
                                                     Optional<UnitOfMeasure> pound) {
        Set<Ingredient> chickenIngredients = new HashSet<>();

        Ingredient powder = new Ingredient();
        powder.setDescription("powder");
        powder.setAmount(BigDecimal.valueOf(2));
        powder.setRecipe(chicken);
        tablespoon.ifPresent(powder::setUom);
        chickenIngredients.add(powder);

        Ingredient oregano = new Ingredient();
        oregano.setDescription("oregano");
        oregano.setAmount(BigDecimal.valueOf(1));
        oregano.setRecipe(chicken);
        teaspoon.ifPresent(oregano::setUom);
        chickenIngredients.add(oregano);

        Ingredient cumin = new Ingredient();
        cumin.setDescription("cumin");
        cumin.setAmount(BigDecimal.valueOf(1));
        cumin.setRecipe(chicken);
        teaspoon.ifPresent(cumin::setUom);
        chickenIngredients.add(cumin);

        Ingredient sugar = new Ingredient();
        sugar.setDescription("sugar");
        sugar.setAmount(BigDecimal.valueOf(1));
        sugar.setRecipe(chicken);
        teaspoon.ifPresent(sugar::setUom);
        chickenIngredients.add(sugar);

        Ingredient salt = new Ingredient();
        salt.setDescription("salt");
        salt.setAmount(BigDecimal.valueOf((double) 1 / 2));
        salt.setRecipe(chicken);
        teaspoon.ifPresent(salt::setUom);
        chickenIngredients.add(salt);

        Ingredient garlic = new Ingredient();
        garlic.setDescription("garlic");
        garlic.setAmount(BigDecimal.valueOf(1));
        garlic.setRecipe(chicken);
        chickenIngredients.add(garlic);

        Ingredient orangeZest = new Ingredient();
        orangeZest.setDescription("orange zest");
        orangeZest.setAmount(BigDecimal.valueOf(1));
        orangeZest.setRecipe(chicken);
        tablespoon.ifPresent(orangeZest::setUom);
        chickenIngredients.add(orangeZest);

        Ingredient orangeJuice = new Ingredient();
        orangeJuice.setDescription("orange juice");
        orangeJuice.setAmount(BigDecimal.valueOf(3));
        orangeJuice.setRecipe(chicken);
        tablespoon.ifPresent(orangeJuice::setUom);
        chickenIngredients.add(orangeJuice);

        Ingredient oliveOil = new Ingredient();
        oliveOil.setDescription("olive oil");
        oliveOil.setAmount(BigDecimal.valueOf(2));
        oliveOil.setRecipe(chicken);
        tablespoon.ifPresent(oliveOil::setUom);
        chickenIngredients.add(oliveOil);

        Ingredient thighs = new Ingredient();
        thighs.setDescription("thighs");
        oliveOil.setAmount(BigDecimal.valueOf(1 + (double) 1 / 4));
        thighs.setRecipe(chicken);
        pound.ifPresent(thighs::setUom);
        chickenIngredients.add(thighs);

        return chickenIngredients;
    }

    private Set<Ingredient> createChickenServeIngredients(Recipe chicken,
                                                          Optional<UnitOfMeasure> ounce,
                                                          Optional<UnitOfMeasure> pint,
                                                          Optional<UnitOfMeasure> cup) {
        Set<Ingredient> chickenServeIngredients = new HashSet<>();

        Ingredient tortillas = new Ingredient();
        tortillas.setDescription("tortillas");
        tortillas.setAmount(BigDecimal.valueOf(8));
        tortillas.setRecipe(chicken);
        chickenServeIngredients.add(tortillas);

        Ingredient arugula = new Ingredient();
        arugula.setDescription("arugula");
        arugula.setAmount(BigDecimal.valueOf(3));
        arugula.setRecipe(chicken);
        ounce.ifPresent(arugula::setUom);
        chickenServeIngredients.add(arugula);

        Ingredient avocado = new Ingredient();
        avocado.setDescription("avocado");
        avocado.setAmount(BigDecimal.valueOf(2));
        avocado.setRecipe(chicken);
        chickenServeIngredients.add(avocado);

        Ingredient radish = new Ingredient();
        radish.setDescription("radish");
        radish.setAmount(BigDecimal.valueOf(4));
        radish.setRecipe(chicken);
        chickenServeIngredients.add(radish);

        Ingredient tomato = new Ingredient();
        tomato.setDescription("tomato");
        tomato.setAmount(BigDecimal.valueOf((double) 1 / 2));
        tomato.setRecipe(chicken);
        pint.ifPresent(tomato::setUom);
        chickenServeIngredients.add(tomato);

        Ingredient onion = new Ingredient();
        onion.setDescription("onion");
        onion.setAmount(BigDecimal.valueOf((double) 1 / 4));
        onion.setRecipe(chicken);
        chickenServeIngredients.add(onion);

        Ingredient cilantro = new Ingredient();
        cilantro.setDescription("cilantro");
        cilantro.setRecipe(chicken);
        chickenServeIngredients.add(cilantro);

        Ingredient sourCream = new Ingredient();
        sourCream.setDescription("sour cream");
        sourCream.setAmount(BigDecimal.valueOf((double) 1 / 2));
        sourCream.setRecipe(chicken);
        cup.ifPresent(sourCream::setUom);
        chickenServeIngredients.add(sourCream);

        Ingredient milk = new Ingredient();
        milk.setDescription("milk");
        milk.setAmount(BigDecimal.valueOf((double) 1 / 4));
        milk.setRecipe(chicken);
        cup.ifPresent(milk::setUom);
        chickenServeIngredients.add(milk);

        Ingredient lime = new Ingredient();
        lime.setDescription("lime");
        lime.setAmount(BigDecimal.valueOf(1));
        lime.setRecipe(chicken);
        chickenServeIngredients.add(lime);

        return chickenServeIngredients;
    }
}
