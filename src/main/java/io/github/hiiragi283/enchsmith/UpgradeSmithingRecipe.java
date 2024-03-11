package io.github.hiiragi283.enchsmith;

import com.google.gson.JsonObject;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Objects;
import java.util.stream.StreamSupport;

public final class UpgradeSmithingRecipe extends SmithingRecipe {
    @NotNull
    public final Ingredient upgrade;
    @NotNull
    public final Enchantment enchantment;

    @NotNull
    private static Ingredient convertInput(@NotNull EnchantmentTarget target) {
        return Ingredient.ofStacks(StreamSupport.stream(Registry.ITEM.spliterator(), false)
                .filter(target::isAcceptableItem)
                .map(Item::getDefaultStack));
    }

    public UpgradeSmithingRecipe(@NotNull Identifier id, @NotNull Ingredient upgrade, @NotNull Enchantment enchantment) {
        super(id, convertInput(enchantment.type), upgrade, ItemStack.EMPTY);
        this.upgrade = upgrade;
        this.enchantment = enchantment;
    }

    // Do not over max enchant level
    @Override
    public boolean matches(@NotNull Inventory inventory, @NotNull World world) {
        return enchantment.type.isAcceptableItem(inventory.getStack(0).getItem())
                && upgrade.test(inventory.getStack(1))
                && EnchantmentHelper.getLevel(enchantment, inventory.getStack(0)) < enchantment.getMaxLevel();
    }

    // Set enchant or add enchant level if present
    @NotNull
    @Override
    public ItemStack craft(@NotNull Inventory inventory) {
        ItemStack result = inventory.getStack(0).copy();
        Map<Enchantment, Integer> enchantMap = EnchantmentHelper.get(result);
        enchantMap.compute(enchantment, (ench, oldLevel) -> (oldLevel == null ? 0 : oldLevel) + 1);
        EnchantmentHelper.set(enchantMap, result);
        return result;
    }

    // Display enchanted book as dummy result
    @NotNull
    @Override
    public ItemStack getOutput() {
        return EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(enchantment, 1));
    }

    //    Serializer    //

    public enum Serializer implements RecipeSerializer<UpgradeSmithingRecipe> {
        INSTANCE;

        @NotNull
        @Override
        public UpgradeSmithingRecipe read(@NotNull Identifier id, @NotNull JsonObject json) {
            Ingredient upgrade = Ingredient.fromJson(JsonHelper.getObject(json, "upgrade"));
            Identifier enchantmentId = new Identifier(JsonHelper.getString(json, "enchantment"));
            if (!Registry.ENCHANTMENT.containsId(enchantmentId)) {
                throw new IllegalStateException("Could not found enchantment; " + enchantmentId);
            }
            Enchantment enchantment = Registry.ENCHANTMENT.get(enchantmentId);
            return new UpgradeSmithingRecipe(id, upgrade, Objects.requireNonNull(enchantment));
        }

        @NotNull
        @Override
        public UpgradeSmithingRecipe read(@NotNull Identifier id, @NotNull PacketByteBuf buf) {
            Ingredient upgrade = Ingredient.fromPacket(buf);
            int enchantmentId = buf.readVarInt();
            Enchantment enchantment = Registry.ENCHANTMENT.get(enchantmentId);
            if (enchantment == null) {
                throw new IllegalStateException("Could not found enchantment; " + enchantmentId);
            }
            return new UpgradeSmithingRecipe(id, upgrade, enchantment);
        }

        @Override
        public void write(@NotNull PacketByteBuf buf, @NotNull UpgradeSmithingRecipe recipe) {
            recipe.upgrade.write(buf);
            buf.writeVarInt(Registry.ENCHANTMENT.getRawId(recipe.enchantment));
        }
    }
}