package io.github.hiiragi283.enchsmith;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.plugin.common.displays.DefaultSmithingDisplay;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Item;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;

import java.util.List;
import java.util.stream.StreamSupport;

@Environment(EnvType.CLIENT)
public final class ESReiPlugin implements REIClientPlugin {
    private DefaultSmithingDisplay convertDisplay(SmithingUpgradeRecipe recipe) {
        return new DefaultSmithingDisplay(
                recipe,
                List.of(
                        EntryIngredients.ofItemStacks(
                                StreamSupport.stream(Registries.ITEM.spliterator(), false)
                                        .filter(item -> recipe.enchantment().target.isAcceptableItem(item))
                                        .map(Item::getDefaultStack)
                                        .toList()
                        ),
                        EntryIngredients.ofIngredient(recipe.upgrade())
                )
        );
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(SmithingUpgradeRecipe.class, RecipeType.SMITHING, this::convertDisplay);
    }
}