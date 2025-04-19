package com.keyboardshop.utils.seeders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.keyboardshop.dao.CategoryDAO;
import com.keyboardshop.dao.ProductDAO;
import com.keyboardshop.enums.Language;
import com.keyboardshop.models.Category;
import com.keyboardshop.models.Product;
import com.keyboardshop.models.ProductAttribute;
import com.keyboardshop.models.ProductAttributeTranslation;
import com.keyboardshop.models.ProductImage;
import com.keyboardshop.models.ProductTranslation;
import com.keyboardshop.utils.SlugUtil;

@Component
public class ProductSeeder
{
    private final ProductDAO productDAO;
    private final CategoryDAO categoryDAO;

    public ProductSeeder(ProductDAO productDAO, CategoryDAO categoryDAO)
    {
        this.productDAO = productDAO;
        this.categoryDAO = categoryDAO;
    }

    public void seed()
    {
        // Keyboards
        this.seedMU01MountainSeclusion();
        this.seedTheDegenerate5108BPlus();
        this.seedMU02Autumn();
        this.seedMU01JoyOfLife();
        this.seedKeychronQ8();


        // Keycaps
        this.seedOtterHustleKeycap();
        this.seedBunnyAppleKeycap();
        this.seedChristmasFantasyKeycap();
        this.seedKuromiKeycap();
        this.seedWatermelonKeycap();
        this.seedKittyPawsKeycap();

        // Switches
        this.seedAkkoAstrolinkMagneticSwitch();
        this.seedAkkoCilantroSwitch();
        this.seedAkkoV3CreamyYellowProSwitch();
        this.seedAkkoV3CreamyBlackProSwitch();
        this.seedAkkoV3MatchaGreenProSwitch();
        this.seedAkkoV3LavenderPurpleProSwitch();

        // Kits
        this.seed5075BVIA();
    }

    // Keyboards
    public void seedMU01MountainSeclusion()
    {
        Category keyboards = this.categoryDAO.getBySlug("keyboards");

        Product product = new Product()
            .setPrice(new BigDecimal(119.99))
            .setName("MU01 Mountain")
            .setDescription(
                "## Multi-Mode\n" +
                "MU01 Mountain Seclusion can be connected to multiple devices through bluetooth 5.0, multi-host 2.4Ghz (with a receiver), and wired Type-C modes with easy switch.\n" + 
                "## Stabilizers\n" + 
                "MU01 Mountain Seclusion comes with purple plate-mounted stabilizers, and is also compatible with screw-in stabilizers.\n" +
                "## Akko Rosewood\n" + 
                "- Type: Linear\n - Operating Force: 40 ± 5gf\n - Total Travel: 4.0mm\n - Pre-Travel: 2.0 ± 0.5mm\n"
            )
            .setCategory(keyboards)
            .setQuantity(10)
            .setSlug(SlugUtil.toSlug("MU01 Mountain Seclusion"));

        ProductTranslation productEN = new ProductTranslation()
            .setLanguage(Language.EN)
            .setName("MU01 Mountain")
            .setDescription(
                "## Multi-Mode\n" +
                "MU01 Mountain Seclusion can be connected to multiple devices through bluetooth 5.0, multi-host 2.4Ghz (with a receiver), and wired Type-C modes with easy switch.\n" + 
                "## Stabilizers\n" + 
                "MU01 Mountain Seclusion comes with purple plate-mounted stabilizers, and is also compatible with screw-in stabilizers.\n" +
                "## Akko Rosewood\n" + 
                "- Type: Linear\n - Operating Force: 40 ± 5gf\n - Total Travel: 4.0mm\n - Pre-Travel: 2.0 ± 0.5mm\n"
            )
            .setProduct(product);

        ProductTranslation productNL = new ProductTranslation()
            .setLanguage(Language.NL)
            .setName("MU01 Mountain Seclusion")
            .setDescription(
                "## Multi-Mode\n" +
                "MU01 Mountain Seclusion kan worden verbonden met meerdere apparaten via bluetooth 5.0, multi-host 2.4Ghz (met een ontvanger) en bedrade Type-C-modi met eenvoudige schakelaar.\n" + 
                "## Stabilisatoren\n" + 
                "MU01 Mountain Seclusion wordt geleverd met paarse plaatgemonteerde stabilisatoren en is ook compatibel met schroefstabilisatoren.\n" +
                "## Akko Rosewood\n" + 
                "- Type: Lineair\n - Bedieningskracht: 40 ± 5gf\n - Totale slag: 4,0 mm\n - Voorloop: 2,0 ± 0,5 mm\n"
            )
            .setProduct(product);

        List<ProductImage> images = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            images.add(new ProductImage()
                .setUrl("MU02-Mountain-Seclusion-GX-" + i + ".jpg")
                .setProduct(product)); 
        }

        ProductAttribute attribute1 = new ProductAttribute()
            .setName("Switch")
            .setValue("Akko V3 Piano Pro/Akko Rosewood")
            .setProduct(product);

        ProductAttributeTranslation attribute1EN = new ProductAttributeTranslation()
            .setLanguage(Language.EN)
            .setName("Switch")
            .setValue("Akko V3 Piano Pro/Akko Rosewood")
            .setProductAttribute(attribute1);

        ProductAttributeTranslation attribute1NL = new ProductAttributeTranslation()
            .setLanguage(Language.NL)
            .setName("Schakelaar")
            .setValue("Akko V3 Piano Pro/Akko Rosewood")
            .setProductAttribute(attribute1);

        attribute1.setTranslations(List.of(attribute1EN, attribute1NL));

        ProductAttribute attribute2 = new ProductAttribute()
            .setName("Interface")
            .setValue("Wireless/Bluetooth/USB Type C")
            .setProduct(product);

        ProductAttributeTranslation attribute2EN = new ProductAttributeTranslation()
            .setLanguage(Language.EN)
            .setName("Interface")
            .setValue("Wireless/Bluetooth/USB Type C")
            .setProductAttribute(attribute2);

        ProductAttributeTranslation attribute2NL = new ProductAttributeTranslation()
            .setLanguage(Language.NL)
            .setName("Interface")
            .setValue("Draadloos/Bluetooth/USB Type C")
            .setProductAttribute(attribute2);

        attribute2.setTranslations(List.of(attribute2EN, attribute2NL));

        ProductAttribute attribute3 = new ProductAttribute()
            .setName("Macro")
            .setValue("Akko Macro V1.0")
            .setProduct(product);

        ProductAttributeTranslation attribute3EN = new ProductAttributeTranslation()
            .setLanguage(Language.EN)
            .setName("Macro")
            .setValue("Akko Macro V1.0")
            .setProductAttribute(attribute3);

        ProductAttributeTranslation attribute3NL = new ProductAttributeTranslation()
            .setLanguage(Language.NL)
            .setName("Macro")
            .setValue("Akko Macro V1.0")
            .setProductAttribute(attribute3);

        attribute3.setTranslations(List.of(attribute3EN, attribute3NL));

        ProductAttribute attribute4 = new ProductAttribute()
            .setName("Hot-Swappable")
            .setValue("Yes")
            .setProduct(product);

        ProductAttributeTranslation attribute4EN = new ProductAttributeTranslation()
            .setLanguage(Language.EN)
            .setName("Hot-Swappable")
            .setValue("Yes")
            .setProductAttribute(attribute4);

        ProductAttributeTranslation attribute4NL = new ProductAttributeTranslation()
            .setLanguage(Language.NL)
            .setName("Hot-Swappable")
            .setValue("Ja")
            .setProductAttribute(attribute4);

        attribute4.setTranslations(List.of(attribute4EN, attribute4NL));


        product.setImages(images);
        product.setTranslations(List.of(productNL, productEN));
        product.setAttributes(List.of(attribute1, attribute2, attribute3, attribute4));
        this.productDAO.create(product);
    }

    public void seedTheDegenerate5108BPlus()
    {
        Category keyboards = this.categoryDAO.getBySlug("keyboards");

        Product product = new Product()
            .setPrice(new BigDecimal(97.64))
            .setName("The Degenerate 5108B Plus")
            .setDescription(
                "## PBT Dye-Sub Keycaps\n" +
                "Made through dye-sub process with durable PBT materials that legends will not fade easily.\n" + 
                "## Multi-Modes\n" + 
                "The Degenerate 5108B Plus can be connected to multiple devices through bluetooth 5.0, multi-host 2.4Ghz (with a receiver), and wired Type-C modes with easy switch.\n" +
                "## Adjustable Heights\n" + 
                "Adjustable height to enhance your typing experience.\n" +
                "The keyboard is supplied with a tilting stand with 3 levels of height adjustment,which allows it to be adapted to the individual needs of the user.\n" + 
                "## Akko V3 Piano Pro\n" +
                "- Type: Linear\n - Operating Force: 45 ± 5gf\n - Total Travel: 3.1mm\n - Pre-Travel: 1.9 ± 0.5mm\n"
            )
            .setCategory(keyboards)
            .setQuantity(10)
            .setSlug(SlugUtil.toSlug("The Degenerate 5108B Plus"));

        ProductTranslation productEN = new ProductTranslation()
            .setLanguage(Language.EN)
            .setName("The Degenerate 5108B Plus")
            .setDescription(
                "## PBT Dye-Sub Keycaps\n" +
                "Made through dye-sub process with durable PBT materials that legends will not fade easily.\n" + 
                "## Multi-Modes\n" + 
                "The Degenerate 5108B Plus can be connected to multiple devices through bluetooth 5.0, multi-host 2.4Ghz (with a receiver), and wired Type-C modes with easy switch.\n" +
                "## Adjustable Heights\n" + 
                "Adjustable height to enhance your typing experience.\n" +
                "The keyboard is supplied with a tilting stand with 3 levels of height adjustment,which allows it to be adapted to the individual needs of the user.\n" + 
                "## Akko V3 Piano Pro\n" +
                "- Type: Linear\n - Operating Force: 45 ± 5gf\n - Total Travel: 3.1mm\n - Pre-Travel: 1.9 ± 0.5mm\n"
            )
            .setProduct(product);

        ProductTranslation productNL = new ProductTranslation()
            .setLanguage(Language.NL)
            .setName("The Degenerate 5108B Plus")
            .setDescription(
                "## PBT Dye-Sub Keycaps\n" +
                "Gemaakt via het dye-sub-proces met duurzame PBT-materialen die legendes niet snel zullen vervagen.\n" + 
                "## Multi-Modes\n" + 
                "De Degenerate 5108B Plus kan worden verbonden met meerdere apparaten via bluetooth 5.0, multi-host 2.4Ghz (met een ontvanger) en bedrade Type-C-modi met eenvoudige schakelaar.\n" +
                "## Verstelbare hoogtes\n" + 
                "Verstelbare hoogte om uw type-ervaring te verbeteren.\n" +
                "Het toetsenbord wordt geleverd met een kantelbare standaard met 3 niveaus van hoogteverstelling, waardoor het kan worden aangepast aan de individuele behoeften van de gebruiker.\n" + 
                "## Akko V3 Piano Pro\n" +
                "- Type: Lineair\n - Bedieningskracht: 45 ± 5gf\n - Totale slag: 3,1 mm\n - Voorloop: 1,9 ± 0,5 mm\n"            
            )
            .setProduct(product);

        List<ProductImage> images = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            images.add(new ProductImage()
                .setUrl("Li-Xingyun-5108B-Plus-" + i + ".jpg")
                .setProduct(product)); 
        }

        ProductAttribute attribute1 = new ProductAttribute()
            .setName("Switch")
            .setValue("Akko V3 Piano Pro Switch")
            .setProduct(product);

        ProductAttributeTranslation attribute1EN = new ProductAttributeTranslation()
            .setLanguage(Language.EN)
            .setName("Switch")
            .setValue("Akko V3 Piano Pro Switch")
            .setProductAttribute(attribute1);

        ProductAttributeTranslation attribute1NL = new ProductAttributeTranslation()
            .setLanguage(Language.NL)
            .setName("Schakelaar")
            .setValue("Akko V3 Piano Pro Switch")
            .setProductAttribute(attribute1);

        attribute1.setTranslations(List.of(attribute1EN, attribute1NL));

        ProductAttribute attribute2 = new ProductAttribute()
            .setName("Interface")
            .setValue("Wireless/Bluetooth/USB Type C")
            .setProduct(product);

        ProductAttributeTranslation attribute2EN = new ProductAttributeTranslation()
            .setLanguage(Language.EN)
            .setName("Interface")
            .setValue("Wireless/Bluetooth/USB Type C")
            .setProductAttribute(attribute2);

        ProductAttributeTranslation attribute2NL = new ProductAttributeTranslation()
            .setLanguage(Language.NL)
            .setName("Interface")
            .setValue("Draadloos/Bluetooth/USB Type C")
            .setProductAttribute(attribute2);

        attribute2.setTranslations(List.of(attribute2EN, attribute2NL));

        ProductAttribute attribute3 = new ProductAttribute()
            .setName("Macro")
            .setValue("Akko Macro V1.0")
            .setProduct(product);

        ProductAttributeTranslation attribute3EN = new ProductAttributeTranslation()
            .setLanguage(Language.EN)
            .setName("Macro")
            .setValue("Akko Macro V1.0")
            .setProductAttribute(attribute3);

        ProductAttributeTranslation attribute3NL = new ProductAttributeTranslation()
            .setLanguage(Language.NL)
            .setName("Macro")
            .setValue("Akko Macro V1.0")
            .setProductAttribute(attribute3);

        attribute3.setTranslations(List.of(attribute3EN, attribute3NL));

        ProductAttribute attribute4 = new ProductAttribute()
            .setName("Hot-Swappable")
            .setValue("Yes")
            .setProduct(product);

        ProductAttributeTranslation attribute4EN = new ProductAttributeTranslation()
            .setLanguage(Language.EN)
            .setName("Hot-Swappable")
            .setValue("Yes")
            .setProductAttribute(attribute4);

        ProductAttributeTranslation attribute4NL = new ProductAttributeTranslation()
            .setLanguage(Language.NL)
            .setName("Hot-Swappable")
            .setValue("Ja")
            .setProductAttribute(attribute4);

        attribute4.setTranslations(List.of(attribute4EN, attribute4NL));

        product.setImages(images);
        product.setTranslations(List.of(productNL, productEN));
        product.setAttributes(List.of(attribute1, attribute2, attribute3, attribute4));
        this.productDAO.create(product);
    }

    public void seedMU02Autumn()
    {
        Category keyboards = this.categoryDAO.getBySlug("keyboards");

        Product product = new Product()
            .setPrice(new BigDecimal(130.19))
            .setName("MU02 Autumn")
            .setDescription(
                "## PBT Dye-Sub Keycaps\n" +
                "Made through dye-sub process with durable PBT materials that legends will not fade easily.\n" +
                "## Multi-Modes\n" + 
                "MU02 Autumn can be connected to multiple devices through bluetooth 5.0, multi-host 2.4Ghz (with a receiver), and wired Type-C modes with easy switch.\n" +
                "Comes with a large 4000mAh battery, it offers strong flexibility in workplace for users with multi-devices such as computers, tablets and mobile devices.\n" +
                "## Akko Rosewood\n" + 
                "- Type: Linear\n - Operating Force: 40 ± 5gf\n - Total Travel: 4.0mm\n - Pre-Travel: 2.0 ± 0.5mm\n"
            )
            .setCategory(keyboards)
            .setQuantity(10)
            .setSlug(SlugUtil.toSlug("MU02 Autumn"));

        ProductTranslation productEN = new ProductTranslation()
            .setLanguage(Language.EN)
            .setName("MU02 Autumn")
            .setDescription(
                "## PBT Dye-Sub Keycaps\n" +
                "Made through dye-sub process with durable PBT materials that legends will not fade easily.\n" +
                "## Multi-Modes\n" + 
                "MU02 Autumn can be connected to multiple devices through bluetooth 5.0, multi-host 2.4Ghz (with a receiver), and wired Type-C modes with easy switch.\n" +
                "Comes with a large 4000mAh battery, it offers strong flexibility in workplace for users with multi-devices such as computers, tablets and mobile devices.\n" +
                "## Akko Rosewood\n" + 
                "- Type: Linear\n - Operating Force: 40 ± 5gf\n - Total Travel: 4.0mm\n - Pre-Travel: 2.0 ± 0.5mm\n"
            )
            .setProduct(product);

        ProductTranslation productNL = new ProductTranslation()
            .setLanguage(Language.NL)
            .setName("MU02 Autumn")
            .setDescription(
                "## PBT Dye-Sub Keycaps\n" +
                "Gemaakt via het dye-sub-proces met duurzame PBT-materialen die legendes niet snel zullen vervagen.\n" +
                "## Multi-Modes\n" + 
                "MU02 Autumn kan worden verbonden met meerdere apparaten via Bluetooth 5.0, multi-host 2.4Ghz (met een ontvanger) en bedrade Type-C-modi met eenvoudige schakelaar.\n" +
                "Wordt geleverd met een grote 4000mAh-batterij en biedt een sterke flexibiliteit op de werkplek voor gebruikers met meerdere apparaten, zoals computers, tablets en mobiele apparaten.\n" +
                "## Akko Rosewood\n" + 
                "- Type: Lineair\n - Bedieningskracht: 40 ± 5gf\n - Totale slag: 4,0 mm\n - Voorloop: 2,0 ± 0,5 mm\n"            
            )
            .setProduct(product);

        List<ProductImage> images = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            images.add(new ProductImage()
                .setUrl("MU02-Autumn-GX-" + i + ".jpg")
                .setProduct(product)); 
        }

        ProductAttribute attribute1 = new ProductAttribute()
            .setName("Switch")
            .setValue("Akko Rosewood")
            .setProduct(product);

        ProductAttributeTranslation attribute1EN = new ProductAttributeTranslation()
            .setLanguage(Language.EN)
            .setName("Switch")
            .setValue("Akko Rosewood")
            .setProductAttribute(attribute1);

        ProductAttributeTranslation attribute1NL = new ProductAttributeTranslation()
            .setLanguage(Language.NL)
            .setName("Schakelaar")
            .setValue("Akko Rosewood")
            .setProductAttribute(attribute1);

        attribute1.setTranslations(List.of(attribute1EN, attribute1NL));

        ProductAttribute attribute2 = new ProductAttribute()
            .setName("Interface")
            .setValue("Wireless/Bluetooth/USB Type C")
            .setProduct(product);

        ProductAttributeTranslation attribute2EN = new ProductAttributeTranslation()
            .setLanguage(Language.EN)
            .setName("Interface")
            .setValue("Wireless/Bluetooth/USB Type C")
            .setProductAttribute(attribute2);

        ProductAttributeTranslation attribute2NL = new ProductAttributeTranslation()
            .setLanguage(Language.NL)
            .setName("Interface")
            .setValue("Draadloos/Bluetooth/USB Type C")
            .setProductAttribute(attribute2);

        attribute2.setTranslations(List.of(attribute2EN, attribute2NL));

        ProductAttribute attribute3 = new ProductAttribute()
            .setName("Macro")
            .setValue("Akko Macro V1.0")
            .setProduct(product);

        ProductAttributeTranslation attribute3EN = new ProductAttributeTranslation()
            .setLanguage(Language.EN)
            .setName("Macro")
            .setValue("Akko Macro V1.0")
            .setProductAttribute(attribute3);

        ProductAttributeTranslation attribute3NL = new ProductAttributeTranslation()
            .setLanguage(Language.NL)
            .setName("Macro")
            .setValue("Akko Macro V1.0")
            .setProductAttribute(attribute3);

        attribute3.setTranslations(List.of(attribute3EN, attribute3NL));

        ProductAttribute attribute4 = new ProductAttribute()
            .setName("Hot-Swappable")
            .setValue("Yes")
            .setProduct(product);

        ProductAttributeTranslation attribute4EN = new ProductAttributeTranslation()
            .setLanguage(Language.EN)
            .setName("Hot-Swappable")
            .setValue("Yes")
            .setProductAttribute(attribute4);

        ProductAttributeTranslation attribute4NL = new ProductAttributeTranslation()
            .setLanguage(Language.NL)
            .setName("Hot-Swappable")
            .setValue("Ja")
            .setProductAttribute(attribute4);

        attribute4.setTranslations(List.of(attribute4EN, attribute4NL));


        product.setImages(images);
        product.setTranslations(List.of(productNL, productEN));
        product.setAttributes(List.of(attribute1, attribute2, attribute3, attribute4));
        this.productDAO.create(product);
    }

    public void seedMU01JoyOfLife()
    {
        Category keyboards = this.categoryDAO.getBySlug("keyboards");

        Product product = new Product()
            .setPrice(new BigDecimal(120.89))
            .setName("MU01 Joy of Life")
            .setDescription(
                "## PBT Dye-Sub Keycaps\n" +
                "Made through dye-sub process with durable PBT materials that legends will not fade easily.\n" +
                "## Multi-Modes\n" + 
                "MU01 Joy of Life can be connected to multiple devices through bluetooth 5.0, multi-host 2.4Ghz (with a receiver), and wired Type-C modes with easy switch.\n" +
                "Comes with a large 4000mAh battery, it offers strong flexibility in workplace for users with multi-devices such as computers, tablets and mobile devices.\n" +
                "## Stabilizers\n" +
                "MU01 Joy of Life comes with purple plate-mounted stabilizers, and is also compatible with screw-in stabilizers.\n" +
                "## Akko Rosewood\n" + 
                "- Type: Linear\n - Operating Force: 38 ± 5gf\n - Total Travel: 3.6mm\n - Pre-Travel: 2.0 ± 0.6mm\n"
            )
            .setCategory(keyboards)
            .setQuantity(10)
            .setSlug(SlugUtil.toSlug("MU01 Joy of Life"));

        ProductTranslation productEN = new ProductTranslation()
            .setLanguage(Language.EN)
            .setName("MU01 Joy of Life")
            .setDescription(
                "## PBT Dye-Sub Keycaps\n" +
                "Made through dye-sub process with durable PBT materials that legends will not fade easily.\n" +
                "## Multi-Modes\n" + 
                "MU01 Joy of Life can be connected to multiple devices through bluetooth 5.0, multi-host 2.4Ghz (with a receiver), and wired Type-C modes with easy switch.\n" +
                "Comes with a large 4000mAh battery, it offers strong flexibility in workplace for users with multi-devices such as computers, tablets and mobile devices.\n" +
                "## Stabilizers\n" +
                "MU01 Joy of Life comes with purple plate-mounted stabilizers, and is also compatible with screw-in stabilizers.\n" +
                "## Akko Rosewood\n" + 
                "- Type: Linear\n - Operating Force: 38 ± 5gf\n - Total Travel: 3.6mm\n - Pre-Travel: 2.0 ± 0.6mm\n"
            )
            .setProduct(product);

        ProductTranslation productNL = new ProductTranslation()
            .setLanguage(Language.NL)
            .setName("MU01 Joy of Life")
            .setDescription(
                "## PBT Dye-Sub Keycaps\n" +
                "Gemaakt via het dye-sub-proces met duurzame PBT-materialen die legendes niet snel zullen vervagen.\n" +
                "## Multi-Modes\n" + 
                "MU02 Autumn kan worden verbonden met meerdere apparaten via Bluetooth 5.0, multi-host 2.4Ghz (met een ontvanger) en bedrade Type-C-modi met eenvoudige schakelaar.\n" +
                "Wordt geleverd met een grote 4000mAh-batterij en biedt een sterke flexibiliteit op de werkplek voor gebruikers met meerdere apparaten, zoals computers, tablets en mobiele apparaten.\n" +
                "## Stabilisatoren\n" +
                "MU01 Joy of Life wordt geleverd met paarse plaatgemonteerde stabilisatoren en is ook compatibel met schroefstabilisatoren.\n" +
                "## Akko Rosewood\n" + 
                "- Type: Lineair\n - Bedieningskracht: 38 ± 5gf\n - Totale slag: 3,6 mm\n - Voorloop: 2,0 ± 0,6 mm\n"            
            )
            .setProduct(product);

        List<ProductImage> images = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            images.add(new ProductImage()
                .setUrl("MU01-Joy-of-Life-" + i + ".jpg")
                .setProduct(product));
        }

        ProductAttribute attribute1 = new ProductAttribute()
            .setName("Switch")
            .setValue("Akko Botany")
            .setProduct(product);

        ProductAttributeTranslation attribute1EN = new ProductAttributeTranslation()
            .setLanguage(Language.EN)
            .setName("Switch")
            .setValue("Akko Botany")
            .setProductAttribute(attribute1);

        ProductAttributeTranslation attribute1NL = new ProductAttributeTranslation()
            .setLanguage(Language.NL)
            .setName("Schakelaar")
            .setValue("Akko Botany")
            .setProductAttribute(attribute1);

        attribute1.setTranslations(List.of(attribute1EN, attribute1NL));

        ProductAttribute attribute2 = new ProductAttribute()
            .setName("Interface")
            .setValue("Wireless/Bluetooth/USB Type C")
            .setProduct(product);

        ProductAttributeTranslation attribute2EN = new ProductAttributeTranslation()
            .setLanguage(Language.EN)
            .setName("Interface")
            .setValue("Wireless/Bluetooth/USB Type C")
            .setProductAttribute(attribute2);

        ProductAttributeTranslation attribute2NL = new ProductAttributeTranslation()
            .setLanguage(Language.NL)
            .setName("Interface")
            .setValue("Draadloos/Bluetooth/USB Type C")
            .setProductAttribute(attribute2);

        attribute2.setTranslations(List.of(attribute2EN, attribute2NL));

        ProductAttribute attribute3 = new ProductAttribute()
            .setName("Macro")
            .setValue("Akko Macro V1.0")
            .setProduct(product);

        ProductAttributeTranslation attribute3EN = new ProductAttributeTranslation()
            .setLanguage(Language.EN)
            .setName("Macro")
            .setValue("Akko Macro V1.0")
            .setProductAttribute(attribute3);

        ProductAttributeTranslation attribute3NL = new ProductAttributeTranslation()
            .setLanguage(Language.NL)
            .setName("Macro")
            .setValue("Akko Macro V1.0")
            .setProductAttribute(attribute3);

        attribute3.setTranslations(List.of(attribute3EN, attribute3NL));

        ProductAttribute attribute4 = new ProductAttribute()
            .setName("Hot-Swappable")
            .setValue("Yes")
            .setProduct(product);

        ProductAttributeTranslation attribute4EN = new ProductAttributeTranslation()
            .setLanguage(Language.EN)
            .setName("Hot-Swappable")
            .setValue("Yes")
            .setProductAttribute(attribute4);

        ProductAttributeTranslation attribute4NL = new ProductAttributeTranslation()
            .setLanguage(Language.NL)
            .setName("Hot-Swappable")
            .setValue("Ja")
            .setProductAttribute(attribute4);

        attribute4.setTranslations(List.of(attribute4EN, attribute4NL));


        product.setImages(images);
        product.setTranslations(List.of(productNL, productEN));
        product.setAttributes(List.of(attribute1, attribute2, attribute3, attribute4));
        this.productDAO.create(product);
    }

    public void seedKeychronQ8()
    {
        Category keyboards = this.categoryDAO.getBySlug("keyboards");
        
        Product product = new Product()
            .setPrice(new BigDecimal(189.00))
            .setName("Keychron Q8 QMK Mechanical Keyboard")
            .setDescription(
                "### Keychron Q8\n" +
                "Keychron Q8 is a 65% Alice layout all-metal mechanical keyboard. With its all-metal CNC machined body, a full-size layout, double-gasket design, QMK/VIA support, and knob option, the Q8 meets all your practical needs and gives you a high-end typing experience.\n"
            )
            .setCategory(keyboards)
            .setQuantity(10)
            .setSlug(SlugUtil.toSlug("Keychron Q8 (Alice Layout) QMK Custom Mechanical Keyboard"));

        ProductTranslation productEN = new ProductTranslation()
            .setLanguage(Language.EN)
            .setName("Keychron Q8 QMK Mechanical Keyboard")
            .setDescription(
                "### Keychron Q8\n" +
                "Keychron Q8 is a 65% Alice layout all-metal mechanical keyboard. With its all-metal CNC machined body, a full-size layout, double-gasket design, QMK/VIA support, and knob option, the Q8 meets all your practical needs and gives you a high-end typing experience.\n"
            )
            .setProduct(product);

        ProductTranslation productNL = new ProductTranslation()
            .setLanguage(Language.NL)
            .setName("Keychron Q8 QMK mechanisch toetsenbord")
            .setDescription(
                "### Keychron Q8\n" +
                "Keychron Q8 is een mechanisch toetsenbord van 65% Alice-indeling, volledig van metaal. Met zijn volledig van metaal gemaakte CNC-gefreesde behuizing, een indeling op ware grootte, dubbel pakkingsontwerp, QMK/VIA-ondersteuning en knopoptie voldoet de Q8 aan al uw praktische behoeften en biedt u een hoogwaardige typervaring.\n"
            )
            .setProduct(product);

        List<ProductImage> images = new ArrayList<>();
        images.add(new ProductImage()
            .setUrl("Q8-C1-Keychron-Q8-QMK-VIA-custom-mechanical-keyboard.jpg")
            .setProduct(product)); 

       ProductAttribute attribute1 = new ProductAttribute()
            .setName("Width")
            .setValue("136mm")
            .setProduct(product);

        ProductAttributeTranslation attribute1EN = new ProductAttributeTranslation()
            .setLanguage(Language.EN)
            .setName("Width")
            .setValue("136mm")
            .setProductAttribute(attribute1);

        ProductAttributeTranslation attribute1NL = new ProductAttributeTranslation()
            .setLanguage(Language.NL)
            .setName("Breedte")
            .setValue("136mm")
            .setProductAttribute(attribute1);
        attribute1.setTranslations(List.of(attribute1EN, attribute1NL));


       ProductAttribute attribute2 = new ProductAttribute()
            .setName("Length")
            .setValue("358mm")
            .setProduct(product);

        ProductAttributeTranslation attribute2EN = new ProductAttributeTranslation()
            .setLanguage(Language.EN)
            .setName("Length")
            .setValue("358mm")
            .setProductAttribute(attribute2);

        ProductAttributeTranslation attribute2NL = new ProductAttributeTranslation()
            .setLanguage(Language.NL)
            .setName("Lengte")
            .setValue("358mm")
            .setProductAttribute(attribute2);
        attribute2.setTranslations(List.of(attribute2EN, attribute2NL));


       ProductAttribute attribute3 = new ProductAttribute()
            .setName("Plate Material")
            .setValue("Steel")
            .setProduct(product);

        ProductAttributeTranslation attribute3EN = new ProductAttributeTranslation()
            .setLanguage(Language.EN)
            .setName("Plate Material")
            .setValue("Steel")
            .setProductAttribute(attribute3);

        ProductAttributeTranslation attribute3NL = new ProductAttributeTranslation()
            .setLanguage(Language.NL)
            .setName("Plaatmateriaal")
            .setValue("Staal")
            .setProductAttribute(attribute3);
        attribute3.setTranslations(List.of(attribute3EN, attribute3NL));

        product.setImages(images);
        product.setTranslations(List.of(productNL, productEN));
        product.setAttributes(List.of(attribute1, attribute2, attribute3));
        this.productDAO.create(product);
    }

    // Keycaps
    public void seedOtterHustleKeycap()
    {
        Category keyboards = this.categoryDAO.getBySlug("keycaps");

        Product product = new Product()
            .setPrice(new BigDecimal(42.77))
            .setName("OtterHustle Keycap Set")
            .setDescription(
                "## PBT Dye-Sub Keycaps\n" +
                "With advanced 5-sided dye sublimation technology, the keycaps feature vibrant and colorful printing across every surface. Made from durable PBT materials, the legends will not fade easily.\n" + 
                "## MOG Mushroom Style Profile\n" + 
                "Akko has introduced the MOG, a new profile with a unique mushroom-style design. This profile, with its rounded edges, offers a special and remarkable typing experience.\n"
            )
            .setCategory(keyboards)
            .setQuantity(10)
            .setSlug(SlugUtil.toSlug("OtterHustle Keycap Set"));

        ProductTranslation productEN = new ProductTranslation()
            .setLanguage(Language.EN)
            .setName("OtterHustle Keycap Set")
            .setDescription(
                "## PBT Dye-Sub Keycaps\n" +
                "With advanced 5-sided dye sublimation technology, the keycaps feature vibrant and colorful printing across every surface. Made from durable PBT materials, the legends will not fade easily.\n" + 
                "## MOG Mushroom Style Profile\n" + 
                "Akko has introduced the MOG, a new profile with a unique mushroom-style design. This profile, with its rounded edges, offers a special and remarkable typing experience.\n"
            )
            .setProduct(product);

        ProductTranslation productNL = new ProductTranslation()
            .setLanguage(Language.NL)
            .setName("OtterHustle Keycap Set")
            .setDescription(
                "## PBT Dye-Sub Keycaps\n" +
                "Met geavanceerde 5-zijdige dye-sublimatietechnologie zijn de keycaps voorzien van levendige en kleurrijke afdrukken op elk oppervlak. Gemaakt van duurzame PBT-materialen, de legendes vervagen niet snel.\n" + 
                "## MOG Mushroom Style Profile\n" + 
                "Akko heeft de MOG geïntroduceerd, een nieuw profiel met een uniek mushroom-style design. Dit profiel, met zijn afgeronde randen, biedt een speciale en opmerkelijke type-ervaring.\n"
            )
            .setProduct(product);

        List<ProductImage> images = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            images.add(new ProductImage()
                .setUrl("OtterHustle-Keycaps-Set-" + i + ".jpg")
                .setProduct(product)); 
        }

        product.setImages(images);
        product.setTranslations(List.of(productNL, productEN));
        this.productDAO.create(product);
    }

    public void seedBunnyAppleKeycap()
    {
        Category keyboards = this.categoryDAO.getBySlug("keycaps");

        Product product = new Product()
            .setPrice(new BigDecimal(42.77))
            .setName("Bunny Apple Keycap Set")
            .setDescription(
                "## PBT Dye-Sub Keycaps\n" +
                "With advanced 5-sided dye sublimation technology, the keycaps feature vibrant and colorful printing across every surface. Made from durable PBT materials, the legends will not fade easily.\n" + 
                "## MOG Mushroom Style Profile\n" + 
                "Akko has introduced the MOG, a new profile with a unique mushroom-style design. This profile, with its rounded edges, offers a special and remarkable typing experience.\n" + 
                "The new MOG keycaps are produced under upgraded moulds with raised facial expressions zones, bringing each character on the keycaps to life."
            )
            .setCategory(keyboards)
            .setQuantity(10)
            .setSlug(SlugUtil.toSlug("Bunny Apple Keycap Set"));

        ProductTranslation productEN = new ProductTranslation()
            .setLanguage(Language.EN)
            .setName("Bunny Apple Keycap Set")
            .setDescription(
                "## PBT Dye-Sub Keycaps\n" +
                "With advanced 5-sided dye sublimation technology, the keycaps feature vibrant and colorful printing across every surface. Made from durable PBT materials, the legends will not fade easily.\n" + 
                "## MOG Mushroom Style Profile\n" + 
                "Akko has introduced the MOG, a new profile with a unique mushroom-style design. This profile, with its rounded edges, offers a special and remarkable typing experience.\n" + 
                "The new MOG keycaps are produced under upgraded moulds with raised facial expressions zones, bringing each character on the keycaps to life."
            )
            .setProduct(product);

        ProductTranslation productNL = new ProductTranslation()
            .setLanguage(Language.NL)
            .setName("Bunny Apple Keycap Set")
            .setDescription(
                "## PBT Dye-Sub Keycaps\n" +
                "Met geavanceerde 5-zijdige dye-sublimatietechnologie zijn de keycaps voorzien van levendige en kleurrijke afdrukken op elk oppervlak. Gemaakt van duurzame PBT-materialen, de legendes vervagen niet snel.\n" + 
                "## MOG Mushroom Style Profile\n" + 
                "Akko heeft de MOG geïntroduceerd, een nieuw profiel met een uniek mushroom-style design. Dit profiel, met zijn afgeronde randen, biedt een speciale en opmerkelijke type-ervaring.\n" +
                "De nieuwe MOG-toetsen worden geproduceerd met verbeterde mallen met verhoogde zones voor gezichtsuitdrukkingen, waardoor elk teken op de toetsen tot leven komt."
            )
            .setProduct(product);

        List<ProductImage> images = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            images.add(new ProductImage()
                .setUrl("Bunny-Apple-Keycap-Set-" + i + ".jpg")
                .setProduct(product)); 
        }

        product.setImages(images);
        product.setTranslations(List.of(productNL, productEN));
        this.productDAO.create(product);
    }
 
    public void seedChristmasFantasyKeycap()
    {
        Category keycaps = this.categoryDAO.getBySlug("keycaps");

        Product product = new Product()
            .setPrice(new BigDecimal(42.77))
            .setName("Christmas Fantasy Keycap Set")
            .setDescription(
                "## PBT Dye-Sub Keycaps\n" +
                "With advanced 5-sided dye sublimation technology, the keycaps feature vibrant and colorful printing across every surface. Made from durable PBT materials, the legends will not fade easily.\n" + 
                "## MOG Mushroom Style Profile\n" + 
                "Akko has introduced the MOG, a new profile with a unique mushroom-style design. This profile, with its rounded edges, offers a special and remarkable typing experience.\n" + 
                "The new MOG keycaps are produced under upgraded moulds with raised facial expressions zones, bringing each character on the keycaps to life."
            )
            .setCategory(keycaps)
            .setQuantity(10)
            .setSlug(SlugUtil.toSlug("Christmas Fantasy Keycap Set"));

        ProductTranslation productEN = new ProductTranslation()
            .setLanguage(Language.EN)
            .setName("Christmas Fantasy Keycap Set")
            .setDescription(
                "## PBT Dye-Sub Keycaps\n" +
                "With advanced 5-sided dye sublimation technology, the keycaps feature vibrant and colorful printing across every surface. Made from durable PBT materials, the legends will not fade easily.\n" + 
                "## MOG Mushroom Style Profile\n" + 
                "Akko has introduced the MOG, a new profile with a unique mushroom-style design. This profile, with its rounded edges, offers a special and remarkable typing experience.\n" + 
                "The new MOG keycaps are produced under upgraded moulds with raised facial expressions zones, bringing each character on the keycaps to life."
            )
            .setProduct(product);

        ProductTranslation productNL = new ProductTranslation()
            .setLanguage(Language.NL)
            .setName("Christmas Fantasy Keycap Set")
            .setDescription(
                "## PBT Dye-Sub Keycaps\n" +
                "Met geavanceerde 5-zijdige dye-sublimatietechnologie zijn de keycaps voorzien van levendige en kleurrijke afdrukken op elk oppervlak. Gemaakt van duurzame PBT-materialen, de legendes vervagen niet snel.\n" + 
                "## MOG Mushroom Style Profile\n" + 
                "Akko heeft de MOG geïntroduceerd, een nieuw profiel met een uniek mushroom-style design. Dit profiel, met zijn afgeronde randen, biedt een speciale en opmerkelijke type-ervaring.\n" +
                "De nieuwe MOG-toetsen worden geproduceerd met verbeterde mallen met verhoogde zones voor gezichtsuitdrukkingen, waardoor elk teken op de toetsen tot leven komt."
            )
            .setProduct(product);

        List<ProductImage> images = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            images.add(new ProductImage()
                .setUrl("Christmas-Fantasy-Keycap-Set-" + i + ".jpg")
                .setProduct(product)); 
        }

        product.setImages(images);
        product.setTranslations(List.of(productNL, productEN));
        this.productDAO.create(product);
    }

    public void seedKuromiKeycap()
    {
        Category keycaps = this.categoryDAO.getBySlug("keycaps");

        Product product = new Product()
            .setPrice(new BigDecimal(55.79))
            .setName("Kuromi Keycap Set")
            .setDescription(
                "## PBT Dye-Sub Keycaps\n" +
                "With advanced 5-sided dye sublimation technology, the keycaps feature vibrant and colorful printing across every surface. Made from durable PBT materials, the legends will not fade easily.\n"
            )
            .setCategory(keycaps)
            .setQuantity(10)
            .setSlug(SlugUtil.toSlug("Kuromi Keycap Set"));

        ProductTranslation productEN = new ProductTranslation()
            .setLanguage(Language.EN)
            .setName("Kuromi Keycap Set")
            .setDescription(
                "## PBT Dye-Sub Keycaps\n" +
                "With advanced 5-sided dye sublimation technology, the keycaps feature vibrant and colorful printing across every surface. Made from durable PBT materials, the legends will not fade easily.\n"
            )
            .setProduct(product);

        ProductTranslation productNL = new ProductTranslation()
            .setLanguage(Language.NL)
            .setName("Kuromi Keycap Set")
            .setDescription(
                "## PBT Dye-Sub Keycaps\n" +
                "Met geavanceerde 5-zijdige dye-sublimatietechnologie zijn de keycaps voorzien van levendige en kleurrijke afdrukken op elk oppervlak. Gemaakt van duurzame PBT-materialen, de legendes vervagen niet snel.\n"
            )
            .setProduct(product);

        List<ProductImage> images = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            images.add(new ProductImage()
                .setUrl("Kuromi-Keycap-Set-138-key-" + i + ".jpg")
                .setProduct(product)); 
        }

        product.setImages(images);
        product.setTranslations(List.of(productNL, productEN));
        this.productDAO.create(product);
    }

    public void seedWatermelonKeycap()
    {
        Category keycaps = this.categoryDAO.getBySlug("keycaps");

        Product product = new Product()
            .setPrice(new BigDecimal(42.77))
            .setName("Watermelon Keycap set")
            .setDescription(
                "## PBT Dye-Sub Keycaps\n" +
                "With advanced 5-sided dye sublimation technology, the keycaps feature vibrant and colorful printing across every surface. Made from durable PBT materials, the legends will not fade easily.\n" +
                "## MOG Mushroom Style Profile\n" + 
                "Akko has introduced the MOG, a new profile with a unique mushroom-style design. This profile, with its rounded edges, offers a special and remarkable typing experience.\n" + 
                "**Disclaimer**: The item sold is keycap set made of PBT and is inedible. Akko is not responsible for any consequences resulting from eating keycaps."
            )
            .setCategory(keycaps)
            .setQuantity(10)
            .setSlug(SlugUtil.toSlug("Watermelon Keycap set"));

        ProductTranslation productEN = new ProductTranslation()
            .setLanguage(Language.EN)
            .setName("Watermelon Keycap set")
            .setDescription(
                "## PBT Dye-Sub Keycaps\n" +
                "With advanced 5-sided dye sublimation technology, the keycaps feature vibrant and colorful printing across every surface. Made from durable PBT materials, the legends will not fade easily.\n" +
                "## MOG Mushroom Style Profile\n" + 
                "Akko has introduced the MOG, a new profile with a unique mushroom-style design. This profile, with its rounded edges, offers a special and remarkable typing experience.\n" + 
                "**Disclaimer**: The item sold is keycap set made of PBT and is inedible. Akko is not responsible for any consequences resulting from eating keycaps."
            )
            .setProduct(product);

        ProductTranslation productNL = new ProductTranslation()
            .setLanguage(Language.NL)
            .setName("Watermelon Keycap set")
            .setDescription(
                "## PBT Dye-Sub Keycaps\n" +
                "Met geavanceerde 5-zijdige dye-sublimatietechnologie zijn de keycaps voorzien van levendige en kleurrijke afdrukken op elk oppervlak. Gemaakt van duurzame PBT-materialen, de legendes vervagen niet snel.\n" +
                "## MOG Mushroom Style Profile\n" + 
                "Akko has introduced the MOG, a new profile with a unique mushroom-style design. This profile, with its rounded edges, offers a special and remarkable typing experience.\n" + 
                "**Disclaimer**: Het verkochte item is een keycapset gemaakt van PBT en is oneetbaar. Akko is niet verantwoordelijk voor de gevolgen van het eten van keycaps."
            )
            .setProduct(product);

        List<ProductImage> images = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            images.add(new ProductImage()
                .setUrl("Watermelon-Keycap-set-138-key-" + i + ".jpg")
                .setProduct(product)); 
        }

        product.setImages(images);
        product.setTranslations(List.of(productNL, productEN));
        this.productDAO.create(product);
    }

    public void seedKittyPawsKeycap()
    {
        Category keycaps = this.categoryDAO.getBySlug("keycaps");

        Product product = new Product()
            .setPrice(new BigDecimal(42.77))
            .setName("Kitty Paws Keycap Set")
            .setDescription(
                "## PBT Dye-Sub Keycaps\n" +
                "With advanced 5-side dye-subbed keycaps with side-printed alpha characters, the keycaps feature vibrant and colorful printing across every surface. Made from durable PBT materials with side-printed legends, the legends will not fade easily.\n" +
                "## MOG Mushroom Style Profile\n" + 
                "Akko Kitty Paws Keycap features MAO Profile non-1u cat ear shaped keys and 1u paw-shaped keys, with cat-ear-like edges, smoother curves, and a pronounced sense of curvature, adding a playful, cat-like atmosphere to your keyboard.\n"
            )
            .setCategory(keycaps)
            .setQuantity(10)
            .setSlug(SlugUtil.toSlug("Kitty Paws Keycap Set"));

        ProductTranslation productEN = new ProductTranslation()
            .setLanguage(Language.EN)
            .setName("Kitty Paws Keycap Set")
            .setDescription(
                "## PBT Dye-Sub Keycaps\n" +
                "With advanced 5-side dye-subbed keycaps with side-printed alpha characters, the keycaps feature vibrant and colorful printing across every surface. Made from durable PBT materials with side-printed legends, the legends will not fade easily.\n" +
                "## MOG Mushroom Style Profile\n" + 
                "Akko Kitty Paws Keycap features MAO Profile non-1u cat ear shaped keys and 1u paw-shaped keys, with cat-ear-like edges, smoother curves, and a pronounced sense of curvature, adding a playful, cat-like atmosphere to your keyboard.\n"
            )
            .setProduct(product);

        ProductTranslation productNL = new ProductTranslation()
            .setLanguage(Language.NL)
            .setName("Kitty Paws Keycap Set")
            .setDescription(
                "## PBT Dye-Sub Keycaps\n" +
                "Met geavanceerde 5-zijdige dye-subbed keycaps met aan de zijkant bedrukte alfa-tekens, hebben de keycaps levendige en kleurrijke afdrukken op elk oppervlak. Gemaakt van duurzame PBT-materialen met aan de zijkant bedrukte legendes, de legendes vervagen niet snel.\n" +
                "## MOG Mushroom Style Profile\n" + 
                "Akko Kitty Paws Keycap heeft MAO Profile non-1u kattenoor-vormige toetsen en 1u poot-vormige toetsen, met kattenoor-achtige randen, vloeiendere rondingen en een uitgesproken gevoel van kromming, wat een speelse, katachtige sfeer aan uw toetsenbord toevoegt.\n"            
            )
            .setProduct(product);

        List<ProductImage> images = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            images.add(new ProductImage()
                .setUrl("Kitty-Paws-Keycap-Set-" + i + ".jpg")
                .setProduct(product)); 
        }

        product.setImages(images);
        product.setTranslations(List.of(productNL, productEN));
        this.productDAO.create(product);
    }

    // Switches
    public void seedAkkoAstrolinkMagneticSwitch()
    {
        Category keyboards = this.categoryDAO.getBySlug("switches");

        Product product = new Product()
            .setPrice(new BigDecimal(22.31))
            .setName("Akko Astrolink Magnetic Switch")
            .setDescription(
                "Astrolink switches are designed for stable and wobble-free key presses, ensuring accuracy during gaming.\n" +
                "Enjoy the satisfyingly firm bottom-out feel and the clean sound profile.\n" + 
                "Featuring a light 36gf initial force, these switches are perfect for gamers who prefer a higher-pitched mechanical style feedback.\n" + 
                "Compatibility: Astrolink switches are compatible with MonsGeek FUN60 Pro & Max HE, FUN60 Ultra HE, FUN60 Ultra TMR, and M1 V5 TMR Magnetic Switch Keyboards, achieving 0.01mm precision. They are NOT compatible with MX mechanical switch PCBs as well as S-pole magnetic switches PCBs.\n" +
                "They should also theoretically be compatible with other downward-facing N-pole magnetic switches. More tests will be rolled out.\n"
            )
            .setCategory(keyboards)
            .setQuantity(10)
            .setSlug(SlugUtil.toSlug("Akko Astrolink Magnetic Switch"));

        ProductTranslation productEN = new ProductTranslation()
            .setLanguage(Language.EN)
            .setName("Akko Astrolink Magnetic Switch")
            .setDescription(
                "Astrolink switches are designed for stable and wobble-free key presses, ensuring accuracy during gaming.\n" +
                "Enjoy the satisfyingly firm bottom-out feel and the clean sound profile.\n" + 
                "Featuring a light 36gf initial force, these switches are perfect for gamers who prefer a higher-pitched mechanical style feedback.\n" + 
                "Compatibility: Astrolink switches are compatible with MonsGeek FUN60 Pro & Max HE, FUN60 Ultra HE, FUN60 Ultra TMR, and M1 V5 TMR Magnetic Switch Keyboards, achieving 0.01mm precision. They are NOT compatible with MX mechanical switch PCBs as well as S-pole magnetic switches PCBs.\n" +
                "They should also theoretically be compatible with other downward-facing N-pole magnetic switches. More tests will be rolled out.\n"
            )
            .setProduct(product);

        ProductTranslation productNL = new ProductTranslation()
            .setLanguage(Language.NL)
            .setName("Akko Astrolink Magnetic Switch")
            .setDescription(
                "Astrolink-switches zijn ontworpen voor stabiele en wiebelvrije toetsaanslagen, wat zorgt voor nauwkeurigheid tijdens het gamen.\n" +
                "Geniet van het bevredigend stevige bottom-out-gevoel en het heldere geluidsprofiel.\n" + 
                "Met een lichte initiële kracht van 36 gf zijn deze switches perfect voor gamers die de voorkeur geven aan een hogere mechanische feedback.\n" + 
                "Compatibiliteit: Astrolink-switches zijn compatibel met MonsGeek FUN60 Pro & Max HE, FUN60 Ultra HE, FUN60 Ultra TMR en M1 V5 TMR Magnetic Switch-toetsenborden, met een precisie van 0,01 mm. Ze zijn NIET compatibel met MX-mechanische switch-PCB's en S-pole magnetische switch-PCB's.\n" +
                "Ze zouden theoretisch ook compatibel moeten zijn met andere naar beneden gerichte N-pole magnetische switches. Er worden meer tests uitgevoerd.\n"
            )
            .setProduct(product);

        List<ProductImage> images = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            images.add(new ProductImage()
                .setUrl("Astrolink-Magnetic-Switch-" + i + ".jpg")
                .setProduct(product)); 
        }

        product.setImages(images);
        product.setTranslations(List.of(productNL, productEN));
        this.productDAO.create(product);
    }

    public void seedAkkoCilantroSwitch()
    {
        Category keyboards = this.categoryDAO.getBySlug("switches");

        Product product = new Product()
            .setPrice(new BigDecimal(12.08))
            .setName("Akko Cilantro Switch")
            .setDescription(
                "Cilantro switches are Akko’s first deep clack tactile switches.\n" +
                "It comes with an early bump at very top, with strong tactile feedback.\n" + 
                "The switch comes with HMX’s latest mould that is meticulously made with enhanced stability.\n" 
            )
            .setCategory(keyboards)
            .setQuantity(10)
            .setSlug(SlugUtil.toSlug("Akko Cilantro Switch"));

        ProductTranslation productEN = new ProductTranslation()
            .setLanguage(Language.EN)
            .setName("Akko Cilantro Switch")
            .setDescription(
                "Cilantro switches are Akko’s first deep clack tactile switches.\n" +
                "It comes with an early bump at very top, with strong tactile feedback.\n" + 
                "The switch comes with HMX’s latest mould that is meticulously made with enhanced stability.\n" 
            )
            .setProduct(product);

        ProductTranslation productNL = new ProductTranslation()
            .setLanguage(Language.NL)
            .setName("Akko Cilantro Switch")
            .setDescription(
                "Cilantro-switches zijn Akko's eerste diepe clack-tactile switches.\n" +
                "Hij wordt geleverd met een vroege bult helemaal bovenaan, met sterke tactiele feedback.\n" + 
                "De switch wordt geleverd met HMX's nieuwste mal die nauwkeurig is gemaakt met verbeterde stabiliteit.\n"           
            )
            .setProduct(product);

        ProductImage image = new ProductImage()
            .setUrl("Akko-Cilantro-Switch.jpg")
            .setProduct(product); 

        product.setImages(List.of(image));
        product.setTranslations(List.of(productNL, productEN));
        this.productDAO.create(product);
    }

    public void seedAkkoV3CreamyYellowProSwitch()
    {
        Category keyboards = this.categoryDAO.getBySlug("switches");

        Product product = new Product()
            .setPrice(new BigDecimal(16.72))
            .setName("Akko V3 Creamy Yellow Pro Switch")
            .setDescription(
                "## Creamy Yellow Pro\n" + 
                "- Type: Linear\n - Operating Force: 50gf ± 10gf\n - Total Travel: 3.3mm\n - Pre Travel: 2.0 ± 0.6mm\n"
            )
            .setCategory(keyboards)
            .setQuantity(10)
            .setSlug(SlugUtil.toSlug("Akko V3 Creamy Yellow Pro Switch"));

        ProductTranslation productEN = new ProductTranslation()
            .setLanguage(Language.EN)
            .setName("Akko V3 Creamy Yellow Pro Switch")
            .setDescription(
                "## Creamy Yellow Pro\n" + 
                "- Type: Linear\n - Operating Force: 50gf ± 10gf\n - Total Travel: 3.3mm\n - Pre Travel: 2.0 ± 0.6mm\n"
            )
            .setProduct(product);

        ProductTranslation productNL = new ProductTranslation()
            .setLanguage(Language.NL)
            .setName("Akko V3 Creamy Yellow Pro Switch")
            .setDescription(
                "## Creamy Yellow Pro\n" + 
                "- Type: Lineair\n - Bedieningskracht: 50gf ± 10gf\n - Totale slag: 3,3 mm\n - Voorslag: 2,0 ± 0,6 mm\n"
            )
            .setProduct(product);

        List<ProductImage> images = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            images.add(new ProductImage()
                .setUrl("V3-Cream-Yellow-Pro-" + i + ".jpg")
                .setProduct(product)); 
        }

        product.setImages(images);
        product.setTranslations(List.of(productNL, productEN));
        this.productDAO.create(product);
    }

    public void seedAkkoV3CreamyBlackProSwitch()
    {
        Category keyboards = this.categoryDAO.getBySlug("switches");

        Product product = new Product()
            .setPrice(new BigDecimal(22.30))
            .setName("Akko V3 Creamy Black Pro Switch")
            .setDescription(
                "## Creamy Black Pro\n" + 
                "- Type: Linear\n - Operating Force: 50gf ± 10gf\n - Total Travel: 3.3mm\n - Pre Travel: 2.0 ± 0.5mm\n"
            )
            .setCategory(keyboards)
            .setQuantity(10)
            .setSlug(SlugUtil.toSlug("Akko V3 Creamy Black Pro Switch"));

        ProductTranslation productEN = new ProductTranslation()
            .setLanguage(Language.EN)
            .setName("Akko V3 Creamy Black Pro Switch")
            .setDescription(
                "## Creamy Black Pro\n" + 
                "- Type: Linear\n - Operating Force: 50gf ± 10gf\n - Total Travel: 3.3mm\n - Pre Travel: 2.0 ± 0.5mm\n"
            )
            .setProduct(product);

        ProductTranslation productNL = new ProductTranslation()
            .setLanguage(Language.NL)
            .setName("Akko V3 Creamy Black Pro Switch")
            .setDescription(
                "## Creamy Black Pro\n" + 
                "- Type: Lineair\n - Bedieningskracht: 50gf ± 10gf\n - Totale slag: 3,3 mm\n - Voorslag: 2,0 ± 0,5 mm\n"
            )
            .setProduct(product);

        ProductImage image = new ProductImage()
            .setUrl("Akko-V3-Creamy-Black-Pro-Switch.jpg")
            .setProduct(product);

        product.setImages(List.of(image));
        product.setTranslations(List.of(productNL, productEN));
        this.productDAO.create(product);
    }

    public void seedAkkoV3MatchaGreenProSwitch()
    {
        Category keyboards = this.categoryDAO.getBySlug("switches");

        Product product = new Product()
            .setPrice(new BigDecimal(18.58))
            .setName("Akko V3 Matcha Green Pro Switch")
            .setDescription(
                "## Matcha Green Pro\n" + 
                "- Type: Linear\n - Operating Force: 50gf ± 10gf\n - Total Travel: 3.8mm\n - Pre Travel: 2.0 ± 0.5mm\n"
            )
            .setCategory(keyboards)
            .setQuantity(10)
            .setSlug(SlugUtil.toSlug("Akko V3 Matcha Green Pro Switch"));

        ProductTranslation productEN = new ProductTranslation()
            .setLanguage(Language.EN)
            .setName("Akko V3 Matcha Green Pro Switch")
            .setDescription(
                "## Matcha Green Pro\n" + 
                "- Type: Linear\n - Operating Force: 50gf ± 10gf\n - Total Travel: 3.8mm\n - Pre Travel: 2.0 ± 0.5mm\n"
            )
            .setProduct(product);

        ProductTranslation productNL = new ProductTranslation()
            .setLanguage(Language.NL)
            .setName("Akko V3 Matcha Green Pro Switch")
            .setDescription(
                "## Matcha Green Pro\n" + 
                "- Type: Lineair\n - Bedieningskracht: 50gf ± 10gf\n - Totale slag: 3,8 mm\n - Voorslag: 2,0 ± 0,5 mm\n"
            )
            .setProduct(product);

        ProductImage image = new ProductImage()
            .setUrl("Akko-V3-Matcha-Green-Pro-Switch.jpg")
            .setProduct(product);

        product.setImages(List.of(image));
        product.setTranslations(List.of(productNL, productEN));
        this.productDAO.create(product);
    }

    public void seedAkkoV3LavenderPurpleProSwitch()
    {
        Category keyboards = this.categoryDAO.getBySlug("switches");

        Product product = new Product()
            .setPrice(new BigDecimal(18.58))
            .setName("Akko V3 Lavender Purple Pro Switch")
            .setDescription(
                "## Lavender Purple Pro\n" + 
                "- Type: Linear\n - Operating Force: 40gf ± 5gf\n - Total Travel: 3.8mm\n - Pre Travel: 1.9 ± 0.5mm\n - Tactile Position:0.5mm\n - Tactile Force: 55gf ± 5gf"
            )
            .setCategory(keyboards)
            .setQuantity(10)
            .setSlug(SlugUtil.toSlug("Akko V3 Lavender Purple Pro Switch"));

        ProductTranslation productEN = new ProductTranslation()
            .setLanguage(Language.EN)
            .setName("Akko V3 Lavender Purple Pro Switch")
            .setDescription(
                "## Lavender Purple Pro\n" + 
                "- Type: Linear\n - Operating Force: 40gf ± 5gf\n - Total Travel: 3.8mm\n - Pre Travel: 1.9 ± 0.5mm\n - Tactile Position:0.5mm\n - Tactile Force: 55gf ± 5gf"
            )
            .setProduct(product);

        ProductTranslation productNL = new ProductTranslation()
            .setLanguage(Language.NL)
            .setName("Akko V3 Lavender Purple Pro Switch")
            .setDescription(
                "## Lavender Purple Pro\n" + 
                "- Type: Lineair\n - Bedieningskracht: 40gf ± 5gf\n - Totale slag: 3,8 mm\n - Voorslag: 1,9 ± 0,5 mm\n - Tactile positie: 0,5 mm - Tactile kracht: 55 gf ± 5 gf"
            )
            .setProduct(product);

        ProductImage image = new ProductImage()
            .setUrl("Akko-V3-Lavender-Purple-Pro-Switch.jpg")
            .setProduct(product);

        product.setImages(List.of(image));
        product.setTranslations(List.of(productNL, productEN));
        this.productDAO.create(product);
    }

    // Kits
    public void seed5075BVIA()
    {
        Category kit = this.categoryDAO.getBySlug("kits");

        Product product = new Product()
            .setPrice(new BigDecimal(22.31))
            .setName("5075B VIA")
            .setDescription(
                "### Multi-Modes\n" +
                "5075B VIA can be connected to multiple devices through BT 5.0, multi-host 2.4Ghz (with a receiver), and wired Type-C modes with easy switch.\n" + 
                "### Programmable RGB Light\n" + 
                "All keys can be re-mapped by Akko Cloud Driver. The keyboard has built-in and side-LED RGB backlit with customizable light animation effects (per-key RGB supported) through VIA.\n"
            )
            .setCategory(kit)
            .setQuantity(10)
            .setSlug(SlugUtil.toSlug("5075B VIA"));

        ProductTranslation productEN = new ProductTranslation()
            .setLanguage(Language.EN)
            .setName("5075B VIA")
            .setDescription(
                "### Multi-Modes\n" +
                "5075B VIA can be connected to multiple devices through BT 5.0, multi-host 2.4Ghz (with a receiver), and wired Type-C modes with easy switch.\n" + 
                "### Programmable RGB Light\n" + 
                "All keys can be re-mapped by Akko Cloud Driver. The keyboard has built-in and side-LED RGB backlit with customizable light animation effects (per-key RGB supported) through VIA.\n"
            )
            .setProduct(product);

        ProductTranslation productNL = new ProductTranslation()
            .setLanguage(Language.NL)
            .setName("5075B VIA")
            .setDescription(
                "### Multi-Modes\n" +
                "5075B VIA kan worden aangesloten op meerdere apparaten via BT 5.0, multi-host 2.4Ghz (met een ontvanger) en bedrade Type-C-modi met eenvoudige schakelaar.\n" + 
                "### Programmeerbaar RGB-licht\n" + 
                "Alle toetsen kunnen opnieuw worden toegewezen door Akko Cloud Driver. Het toetsenbord heeft ingebouwde en zij-LED RGB-achtergrondverlichting met aanpasbare lichtanimatie-effecten (RGB per toets ondersteund) via VIA.\n"            )
            .setProduct(product);

        List<ProductImage> images = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            images.add(new ProductImage()
                .setUrl("5075B-Via-" + i + ".jpg")
                .setProduct(product)); 
        }

        product.setImages(images);
        product.setTranslations(List.of(productNL, productEN));
        this.productDAO.create(product);      
    }
}
