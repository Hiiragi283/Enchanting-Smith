package io.github.hiiragi283.enchsmith;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.IRequirementsStrategy;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public final class UpgradeSmithingRecipeBuilder {

    @NotNull
    private final EnchantmentType target;
    @NotNull
    private final Ingredient upgrade;
    @NotNull
    private final Enchantment enchantment;

    public UpgradeSmithingRecipeBuilder(@NotNull EnchantmentType target, @NotNull Ingredient upgrade, @NotNull Enchantment enchantment) {
        this.target = target;
        this.upgrade = upgrade;
        this.enchantment = enchantment;
    }

    @NotNull
    private final Advancement.Builder builder = Advancement.Builder.advancement();

    @NotNull
    public UpgradeSmithingRecipeBuilder unlocks(@NotNull String name, @NotNull ICriterionInstance criterionInstance) {
        builder.addCriterion(name, criterionInstance);
        return this;
    }

    public void offerTo(@NotNull Consumer<IFinishedRecipe> exporter, @NotNull ResourceLocation recipeLocation) {
        builder.parent(new ResourceLocation("recipes/root"))
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeLocation))
                .rewards(AdvancementRewards.Builder.recipe(recipeLocation))
                .requirements(IRequirementsStrategy.OR);
        exporter.accept(new Provider(recipeLocation, target, upgrade, enchantment, builder.serializeToJson()));
    }

    public static final class Provider implements IFinishedRecipe {

        @NotNull
        private final ResourceLocation recipeLocation;
        @NotNull
        private final EnchantmentType target;
        @NotNull
        private final Ingredient upgrade;
        @NotNull
        private final Enchantment enchantment;
        @NotNull
        private final JsonObject advancement;
        @NotNull
        private final ResourceLocation advancementId;

        public Provider(@NotNull ResourceLocation recipeLocation, @NotNull EnchantmentType target, @NotNull Ingredient upgrade, @NotNull Enchantment enchantment, @NotNull JsonObject advancement) {
            this.recipeLocation = recipeLocation;
            this.target = target;
            this.upgrade = upgrade;
            this.enchantment = enchantment;
            this.advancement = advancement;
            this.advancementId = new ResourceLocation(recipeLocation.getNamespace(), "recipes/" + recipeLocation.getPath());
        }

        @Override
        public void serializeRecipeData(@NotNull JsonObject json) {
            json.addProperty("target", target.name());
            json.add("upgrade", upgrade.toJson());
            ResourceLocation enchantmentLocation = enchantment.getRegistryName();
            String enchantmentName = enchantmentLocation == null ? "null" : enchantmentLocation.toString();
            json.addProperty("enchantment", enchantmentName);
        }

        @NotNull
        @Override
        public ResourceLocation getId() {
            return recipeLocation;
        }

        @NotNull
        @Override
        public IRecipeSerializer<?> getType() {
            return UpgradeSmithingRecipe.Serializer.INSTANCE;
        }

        @NotNull
        @Override
        public JsonObject serializeAdvancement() {
            return advancement;
        }

        @NotNull
        @Override
        public ResourceLocation getAdvancementId() {
            return advancementId;
        }
    }
}