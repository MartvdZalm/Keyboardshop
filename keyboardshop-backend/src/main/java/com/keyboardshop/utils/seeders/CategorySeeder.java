package com.keyboardshop.utils.seeders;

import java.util.List;

import org.springframework.stereotype.Component;

import com.keyboardshop.dao.CategoryDAO;
import com.keyboardshop.enums.Language;
import com.keyboardshop.models.Category;
import com.keyboardshop.models.CategoryTranslation;

@Component
public class CategorySeeder
{

    private final CategoryDAO categoryDAO;

    public CategorySeeder(CategoryDAO categoryDAO)
    {
        this.categoryDAO = categoryDAO;
    }

    public void seed()
    {
        Category keyboards = new Category()
            .setSlug("keyboards")
            .setImage("category-keyboard.jpg");
        CategoryTranslation keyboardsNL = new CategoryTranslation()
            .setLanguage(Language.NL)
            .setName("Toetsenborden")
            .setDescription("Volledig geassembleerde mechanische toetsenborden.")
            .setCategory(keyboards);
        CategoryTranslation keyboardsEN = new CategoryTranslation()
            .setLanguage(Language.EN)
            .setName("Keyboards")
            .setDescription("Fully assembled mechanical keyboards.")
            .setCategory(keyboards);

        keyboards.setTranslations(List.of(keyboardsNL, keyboardsEN));
        this.categoryDAO.create(keyboards);


        Category keycaps = new Category()
            .setSlug("keycaps")
            .setImage("category-keycaps.jpg");
        CategoryTranslation keycapsNL = new CategoryTranslation()
            .setLanguage(Language.NL)
            .setName("Keycaps")
            .setDescription("Sets met verwisselbare toetsen in verschillende kleuren, materialen en profielen om het uiterlijk en gevoel van uw toetsenbord te personaliseren.")
            .setCategory(keycaps);
        CategoryTranslation keycapsEN = new CategoryTranslation()
            .setLanguage(Language.EN)
            .setName("Keycaps")
            .setDescription("Sets of interchangeable keycaps in various colors, materials, and profiles to customize the look and feel of your keyboard.")
            .setCategory(keycaps);

        keycaps.setTranslations(List.of(keycapsNL, keycapsEN));
        this.categoryDAO.create(keycaps);


        Category switches = new Category()
            .setSlug("switches")
            .setImage("category-switches.jpg");
        CategoryTranslation switchesNL = new CategoryTranslation()
            .setLanguage(Language.NL)
            .setName("Switches")
            .setDescription("Mechanische schakelaars die het gevoel en de feedback van toetsaanslagen bepalen, verkrijgbaar in lineaire, tactiele en klikkende varianten.")
            .setCategory(switches);
        CategoryTranslation switchesEN = new CategoryTranslation()
            .setLanguage(Language.EN)
            .setName("Switches")
            .setDescription("Mechanical switches that determine keypress feel and feedback, available in linear, tactile, and clicky varieties.")
            .setCategory(switches);

        switches.setTranslations(List.of(switchesNL, switchesEN));
        this.categoryDAO.create(switches); 


        Category kits = new Category()
            .setSlug("kits")
            .setImage("category-kit.jpg");
        CategoryTranslation kitsNL = new CategoryTranslation()
            .setLanguage(Language.NL)
            .setName("Kits")
            .setDescription("Zelfbouw-toetsenbordpakketten met essentiële onderdelen zoals de behuizing en de printplaat, ideaal voor het bouwen van een toetsenbord op maat.")
            .setCategory(kits);
        CategoryTranslation kitsEN = new CategoryTranslation()
            .setLanguage(Language.EN)
            .setName("Kits")
            .setDescription("DIY keyboard kits that include essential components like the case and PCB, perfect for building a custom keyboard.")
            .setCategory(kits);

        kits.setTranslations(List.of(kitsNL, kitsEN));
        this.categoryDAO.create(kits);     
    }
}
