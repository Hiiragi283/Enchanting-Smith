package io.github.hiiragi283.enchsmith;

import com.google.gson.JsonObject;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.CriterionMerger;
import net.minecraft.advancement.criterion.CriterionConditions;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public final class SmithingUpgradeRecipeJsonBuilder {
    @NotNull
    private final Ingredient upgrade;
    @NotNull
    private final Enchantment enchantment;

    public SmithingUpgradeRecipeJsonBuilder(@NotNull Ingredient upgrade, @NotNull Enchantment enchantment) {
        this.upgrade = upgrade;
        this.enchantment = enchantment;
    }

    public static void createSimple(@NotNull Item item, @NotNull Enchantment enchantment, @NotNull Identifier recipeId, @NotNull Consumer<RecipeJsonProvider> exporter) {
        new SmithingUpgradeRecipeJsonBuilder(Ingredient.ofItems(item), enchantment)
                .criterion("has_upgrade", RecipeProvider.conditionsFromItem(item))
                .offerTo(exporter, recipeId);
    }

    public static void createSimple(@NotNull TagKey<Item> tagKey, @NotNull Enchantment enchantment, @NotNull Identifier recipeId, @NotNull Consumer<RecipeJsonProvider> exporter) {
        new SmithingUpgradeRecipeJsonBuilder(Ingredient.fromTag(tagKey), enchantment)
                .criterion("has_upgrade", RecipeProvider.conditionsFromTag(tagKey))
                .offerTo(exporter, recipeId);
    }

    @NotNull
    private final Advancement.Builder builder = Advancement.Builder.createUntelemetered();

    @NotNull
    public SmithingUpgradeRecipeJsonBuilder criterion(@NotNull String name, @NotNull CriterionConditions conditions) {
        builder.criterion(name, conditions);
        return this;
    }

    public void offerTo(@NotNull Consumer<RecipeJsonProvider> exporter, @NotNull Identifier recipeId) {
        builder.parent(new Identifier("recipes/root"))
                .criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .criteriaMerger(CriterionMerger.OR);
        exporter.accept(new Provider(recipeId, upgrade, enchantment, builder.toJson(), recipeId.withPrefixedPath("recipes/")));
    }

    public record Provider(
            @NotNull
            Identifier recipeId,
            @NotNull
            Ingredient upgrade,
            @NotNull
            Enchantment enchantment,
            @NotNull
            JsonObject advancement,
            @NotNull
            Identifier advancementId
    ) implements RecipeJsonProvider {

        @Override
        public void serialize(@NotNull JsonObject json) {
            json.add("upgrade", upgrade.toJson());
            Identifier enchantmentId = Registries.ENCHANTMENT.getId(enchantment);
            String enchantmentName = enchantmentId == null ? "null" : enchantmentId.toString();
            json.addProperty("enchantment", enchantmentName);
        }

        @NotNull
        @Override
        public Identifier getRecipeId() {
            return recipeId;
        }

        @NotNull
        @Override
        public RecipeSerializer<?> getSerializer() {
            return SmithingUpgradeRecipe.Serializer.INSTANCE;
        }

        @NotNull
        @Override
        public JsonObject toAdvancementJson() {
            return advancement;
        }

        @NotNull
        @Override
        public Identifier getAdvancementId() {
            return advancementId;
        }
    }
}