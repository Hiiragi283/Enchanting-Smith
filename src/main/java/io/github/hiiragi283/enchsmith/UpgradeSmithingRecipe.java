package io.github.hiiragi283.enchsmith;

import com.google.gson.JsonObject;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.SmithingRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Objects;
import java.util.stream.StreamSupport;

public final class UpgradeSmithingRecipe extends SmithingRecipe {

    @NotNull
    public final EnchantmentType target;
    @NotNull
    public final Ingredient upgrade;
    @NotNull
    public final Enchantment enchantment;

    @NotNull
    private static Ingredient convertInput(@NotNull EnchantmentType target) {
        return Ingredient.of(StreamSupport.stream(ForgeRegistries.ITEMS.spliterator(), false)
                .filter(target::canEnchant)
                .map(Item::getDefaultInstance));
    }

    public UpgradeSmithingRecipe(@NotNull ResourceLocation location, @NotNull EnchantmentType target, @NotNull Ingredient upgrade, @NotNull Enchantment enchantment) {
        super(location, convertInput(target), upgrade, ItemStack.EMPTY);
        this.target = target;
        this.upgrade = upgrade;
        this.enchantment = enchantment;
    }

    // Do not over max enchant level
    @Override
    public boolean matches(@NotNull IInventory inventory, @NotNull World world) {
        return target.canEnchant(inventory.getItem(0).getItem())
                && upgrade.test(inventory.getItem(1))
                && EnchantmentHelper.getItemEnchantmentLevel(enchantment, inventory.getItem(0)) < enchantment.getMaxLevel();
    }

    // Set enchant or add enchant level if present
    @NotNull
    @Override
    public ItemStack assemble(@NotNull IInventory inventory) {
        ItemStack result = inventory.getItem(0).copy();
        Map<Enchantment, Integer> enchantMap = EnchantmentHelper.getEnchantments(result);
        enchantMap.compute(enchantment, (ench, oldLevel) -> (oldLevel == null ? 0 : oldLevel) + 1);
        EnchantmentHelper.setEnchantments(enchantMap, result);
        return result;
    }

    // Display enchanted book as dummy result
    @NotNull
    @Override
    public ItemStack getResultItem() {
        return EnchantedBookItem.createForEnchantment(new EnchantmentData(enchantment, 1));
    }

    //    Serializer    //

    public enum Serializer implements IRecipeSerializer<UpgradeSmithingRecipe> {
        INSTANCE;

        @NotNull
        @Override
        public UpgradeSmithingRecipe fromJson(@NotNull ResourceLocation location, @NotNull JsonObject json) {
            EnchantmentType target = EnchantmentType.valueOf(JSONUtils.getAsString(json, "target"));
            Ingredient upgrade = Ingredient.fromJson(JSONUtils.getAsJsonObject(json, "upgrade"));
            ResourceLocation enchantmentId = new ResourceLocation(JSONUtils.getAsString(json, "enchantment"));
            if (!ForgeRegistries.ENCHANTMENTS.containsKey(enchantmentId)) {
                throw new IllegalStateException("Could not found enchantment; " + enchantmentId);
            }
            Enchantment enchantment = ForgeRegistries.ENCHANTMENTS.getValue(enchantmentId);
            return new UpgradeSmithingRecipe(location, target, upgrade, Objects.requireNonNull(enchantment));
        }

        @NotNull
        @Override
        public UpgradeSmithingRecipe fromNetwork(@NotNull ResourceLocation location, @NotNull PacketBuffer buf) {
            EnchantmentType target = EnchantmentType.valueOf(buf.readUtf());
            Ingredient upgrade = Ingredient.fromNetwork(buf);
            ResourceLocation enchantmentLocation = new ResourceLocation(buf.readUtf());
            Enchantment enchantment = ForgeRegistries.ENCHANTMENTS.getValue(enchantmentLocation);
            if (enchantment == null) {
                throw new IllegalStateException("Could not found enchantment; " + enchantmentLocation);
            }
            return new UpgradeSmithingRecipe(location, target, upgrade, enchantment);
        }

        @Override
        public void toNetwork(@NotNull PacketBuffer buf, @NotNull UpgradeSmithingRecipe recipe) {
            buf.writeUtf(recipe.target.name());
            recipe.upgrade.toNetwork(buf);
            buf.writeUtf(Objects.requireNonNull(recipe.enchantment.getRegistryName()).toString());
        }

        @NotNull
        @Override
        public IRecipeSerializer<?> setRegistryName(ResourceLocation name) {
            return this;
        }

        @NotNull
        @Override
        public ResourceLocation getRegistryName() {
            return new ResourceLocation(EnchantingSmith.MOD_ID, "upgrade");
        }

        @NotNull
        @SuppressWarnings("unchecked")
        private <T> Class<T> castClass() {
            return (Class<T>) Serializer.class;
        }

        @NotNull
        @Override
        public Class<IRecipeSerializer<?>> getRegistryType() {
            return castClass();
        }
    }
}