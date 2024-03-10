package io.github.hiiragi283.enchsmith;

import com.google.gson.JsonObject;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.CriterionMerger;
import net.minecraft.advancement.criterion.CriterionConditions;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public final class UpgradeSmithingRecipeJsonFactory {

    @NotNull
    private final EnchantmentTarget target;
    @NotNull
    private final Ingredient upgrade;
    @NotNull
    private final Enchantment enchantment;

    public UpgradeSmithingRecipeJsonFactory(@NotNull EnchantmentTarget target, @NotNull Ingredient upgrade, @NotNull Enchantment enchantment) {
        this.target = target;
        this.upgrade = upgrade;
        this.enchantment = enchantment;
    }

    @NotNull
    private final Advancement.Task builder = Advancement.Task.create();

    @NotNull
    public UpgradeSmithingRecipeJsonFactory criterion(@NotNull String name, @NotNull CriterionConditions conditions) {
        builder.criterion(name, conditions);
        return this;
    }

    public void offerTo(@NotNull Consumer<RecipeJsonProvider> exporter, @NotNull Identifier recipeId) {
        builder.parent(new Identifier("recipes/root"))
                .criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .criteriaMerger(CriterionMerger.OR);
        exporter.accept(new Provider(recipeId, target, upgrade, enchantment, builder.toJson()));
    }

    public static final class Provider implements RecipeJsonProvider {

        @NotNull
        private final Identifier recipeId;
        @NotNull
        private final EnchantmentTarget target;
        @NotNull
        private final Ingredient upgrade;
        @NotNull
        private final Enchantment enchantment;
        @NotNull
        private final JsonObject advancement;
        @NotNull
        private final Identifier advancementId;

        public Provider(@NotNull Identifier recipeId, @NotNull EnchantmentTarget target, @NotNull Ingredient upgrade, @NotNull Enchantment enchantment, @NotNull JsonObject advancement) {
            this.recipeId = recipeId;
            this.target = target;
            this.upgrade = upgrade;
            this.enchantment = enchantment;
            this.advancement = advancement;
            this.advancementId = new Identifier(recipeId.getNamespace(), "recipes/" + recipeId.getPath());
        }

        @Override
        public void serialize(@NotNull JsonObject json) {
            json.addProperty("target", target.name());
            json.add("upgrade", upgrade.toJson());
            Identifier enchantmentId = Registry.ENCHANTMENT.getId(enchantment);
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
            return UpgradeSmithingRecipe.Serializer.INSTANCE;
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