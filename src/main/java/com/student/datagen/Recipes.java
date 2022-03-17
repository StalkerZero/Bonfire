package com.student.datagen;

import com.student.setup.Registration;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

import static com.student.setup.Items.Coiled_Sword;

public class Recipes extends RecipeProvider {
    public Recipes(DataGenerator p_125973_) {
        super(p_125973_);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> p_176532_) {
        ShapedRecipeBuilder.shaped(Registration.Coiled_Sword.get())
                .pattern(" o ")
                .pattern(" o ")
                .pattern(" i ")
                .define('o', Tags.Items.OBSIDIAN)
                .define('i', Items.IRON_INGOT)
                .group("tutorial")
                .unlockedBy("iron_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                .save(p_176532_);
        ShapedRecipeBuilder.shaped(Registration.Bonfire.get())
                .pattern("   ")
                .pattern(" s ")
                .pattern(" c ")
                .define('s', Coiled_Sword)
                .define('c', Items.CAMPFIRE)
                .group("tutorial")
                .unlockedBy("campfire", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CAMPFIRE))
                .save(p_176532_);
    }
}
