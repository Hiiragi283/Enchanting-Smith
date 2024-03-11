package io.github.hiiragi283.enchsmith;

import com.google.gson.JsonObject;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Objects;

public record SmithingUpgradeRecipe(
        @NotNull
        Identifier id,
        @NotNull
        Ingredient upgrade,
        @NotNull
        Enchantment enchantment
) implements SmithingRecipe {
    @Override
    public boolean matches(@NotNull Inventory inventory, @NotNull World world) {
        return testTemplate(inventory.getStack(0))
                && testBase(inventory.getStack(1))
                && testAddition(inventory.getStack(2));
    }

    // Set enchant or add enchant level if present
    @NotNull
    @Override
    public ItemStack craft(Inventory inventory, DynamicRegistryManager registryManager) {
        ItemStack result = inventory.getStack(1).copy();
        Map<Enchantment, Integer> enchantMap = EnchantmentHelper.get(result);
        enchantMap.compute(enchantment, (ench, oldLevel) -> (oldLevel == null ? 0 : oldLevel) + 1);
        EnchantmentHelper.set(enchantMap, result);
        return result;
    }

    // Display enchanted book as dummy result
    @NotNull
    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(enchantment, 1));
    }

    @NotNull
    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public boolean testTemplate(ItemStack stack) {
        return stack.isEmpty();
    }

    // Do not over max enchant level
    @Override
    public boolean testBase(ItemStack stack) {
        return enchantment.target.isAcceptableItem(stack.getItem()) && EnchantmentHelper.getLevel(enchantment, stack) < enchantment.getMaxLevel();
    }

    @Override
    public boolean testAddition(ItemStack stack) {
        return upgrade.test(stack);
    }

    //    Serializer    //

    public enum Serializer implements RecipeSerializer<SmithingUpgradeRecipe> {
        INSTANCE;

        @NotNull
        @Override
        public SmithingUpgradeRecipe read(@NotNull Identifier id, @NotNull JsonObject json) {
            Ingredient upgrade = Ingredient.fromJson(JsonHelper.getObject(json, "upgrade"));
            Identifier enchantmentId = new Identifier(JsonHelper.getString(json, "enchantment"));
            if (!Registries.ENCHANTMENT.containsId(enchantmentId)) {
                throw new IllegalStateException("Could not found enchantment; " + enchantmentId);
            }
            Enchantment enchantment = Registries.ENCHANTMENT.get(enchantmentId);
            return new SmithingUpgradeRecipe(id, upgrade, Objects.requireNonNull(enchantment));
        }

        @NotNull
        @Override
        public SmithingUpgradeRecipe read(@NotNull Identifier id, @NotNull PacketByteBuf buf) {
            Ingredient upgrade = Ingredient.fromPacket(buf);
            int enchantmentId = buf.readVarInt();
            Enchantment enchantment = Registries.ENCHANTMENT.get(enchantmentId);
            if (enchantment == null) {
                throw new IllegalStateException("Could not found enchantment; " + enchantmentId);
            }
            return new SmithingUpgradeRecipe(id, upgrade, enchantment);
        }

        @Override
        public void write(@NotNull PacketByteBuf buf, @NotNull SmithingUpgradeRecipe recipe) {
            recipe.upgrade.write(buf);
            buf.writeVarInt(Registries.ENCHANTMENT.getRawId(recipe.enchantment));
        }
    }
}