package com.haruma.registry;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class ModRecipe extends RecipeProvider {
    public ModRecipe(PackOutput output, CompletableFuture<HolderLookup.Provider> registries){
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput output){
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SMILE.get(), 1)
                .pattern("YYY")
                .pattern("BYB")
                .pattern("YYY")
                .define('Y', Items.YELLOW_DYE)
                .define('B', Items.BLACK_DYE)
                .unlockedBy("has_yellow_dye", has(Items.YELLOW_DYE))
                .save(output);
    }
}
